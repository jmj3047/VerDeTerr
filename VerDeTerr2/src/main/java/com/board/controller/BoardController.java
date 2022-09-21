package com.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.domain.CharacterDTO;
import com.board.domain.TypeDTO;
import com.board.domain.UserDTO;
import com.board.service.BoardService;
import com.board.service.CharacterService;
import com.board.service.SurveyService;
import com.board.util.UiUtils;

@Controller // 해당 클래스가 사용자의 요청과 응답을 처리하는 , 즉 UI를 담당하는 컨트롤러 클래스임을 의미.
public class BoardController extends UiUtils {
   
   @Autowired
   private BoardService boardService;

   @Autowired
   private CharacterService characterService;

   @GetMapping(value = "/board/select")
   public String selectBoardType() {
      return "board/select";
   }

   // value 값으로 호출하면 openboardlist 함수가 실행

   @GetMapping(value = "/board/list")
   public String openBoardList(HttpSession session, @RequestParam(required = false) String type,
         /* ModelAttribute 를 통해서, params 라는 이름으로 list.html 단으로 보낸다. */@ModelAttribute("params") BoardDTO params,
         Model model) {
      // 여기서 PostType에 type를 넣어준다.
      System.out.println("board/list controller에 들어오고, type은 잘가져오는지" + type);
      params.setPostType(type);
      System.out.println("그렇다면 params에 잘 넣는지 여기가 중요하다" + params);
      List<BoardDTO> boardList = boardService.getBoardList(params);
      model.addAttribute("boardlist", boardList);

      // type 으로 list.html 로 보내야한다. 그러면 거기서 isMytype을 통해서 write를 쓸때 type 을 전달해준다.
      model.addAttribute("type", type);

      List<CharacterDTO> charList = characterService.getCharacterList(type);
      CharacterDTO character = charList.get(charList.size() - 1);
      model.addAttribute("charName", character.getName());
      model.addAttribute("charImg", character.getFilepath());

      UserDTO user = (UserDTO) session.getAttribute("user");
      // user가 있을떄(로그인된 상태)

      if (user != null) { // 로그인이 되었다면0
         model.addAttribute("isManager", user.isManagerYn());
         System.out.println(user.getUserType());
         if (user.getUserType() == null) { // 로그인한 유저의 타입이 없다면 == 테스트를 안했다면
            model.addAttribute("isSurvey", "false");
            return "board/list";
         }

         // 로그인유저의 테스트 이후
         System.out.println("getusertype" + user.getUserType());
         System.out.println(type);
         // 로그인 된 상태에서, type이 다르면 글작성 할 수 없게 하기위해 보내는 user의 type
         if (!user.getUserType().equals(type)) {
            // 유저의 타입과 게시판의 타입이 다르면 권한을 주지 않기 위해서
            model.addAttribute("isMyType", "f");
         }
      }
      
      // 로그인이 안됐으면바로 리턴.
      return "board/list";
   }

   @GetMapping(value = "/board/view")
   public String openBoardDetail(HttpSession session, @RequestParam(required = false) String type,
         @RequestParam(value = "idx", required = false) Long idx, Model model) {
      if (idx == null) {
         // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
         return "redirect:/board/list";
      }

      UserDTO user = (UserDTO) session.getAttribute("user");
      if(user != null) {
         model.addAttribute("isManager", user.isManagerYn());
         model.addAttribute("myNickname", user.getNickname());
         if (user.getUserType() != null) { // 로그인한 유저의 타입이 없다면 == 테스트를 안했다면
            model.addAttribute("isSurvey", "true");
         }
      }
      BoardDTO board = boardService.getBoardDetail(idx);
      if (board == null || board.getDeleteYn()) {
         // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
         return "redirect:/board/list";
      }
      System.out.println("board.idx:" + board.getIdx());
      model.addAttribute("board", board);
      model.addAttribute("type", type);

      return "board/view";
   }

   @GetMapping(value = "/board/write") // 그냥 내가 정하는 요청 url 이렇게 요청하면 return 경로로 갈거에요.
   public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx,
         @RequestParam(required = false) String type, Model model, HttpSession session) { // 새로운 게시글을 등록하는 경우에는 게시글
      // 번호(idx) 가 필요하지 않기 때문에
      // required 속성을 false
      UserDTO user = (UserDTO) session.getAttribute("user");

      if (user != null) {
         if (idx == null) {
            // addAttribute BoardDTO 객체를 "board" 라는 이름으로 뷰(화면으로) 전달
            // 게시글 번호(idx)가 전송되지 않은 경우에는 비어있는 객체를 전달하고, 게시글번호가
            // 전송된 경우에는 getBoardDetail 메서드의 실행결과 ,즉 게시글 정보를 포함하고 있는 객체를 전달
            // 만약 getBoardDetail 메서드의 실행결과가 null 이면, 게시글 리스트 페이지로 리다이렉트 합니다.
            BoardDTO dao1 = new BoardDTO();// dao1 이라는 새로운 인스턴스를 생성
            dao1.setWriter(user.getNickname());// 그 BoardDTO 에 닮겨있는 writer 에 dao1을 통해 writer를 지정
            System.out.println("제발 되었으면" + dao1.getWriter());
            model.addAttribute("board", dao1); // "board" 라는 key 에 dao1의 value를 입력

         } else {
            BoardDTO board = boardService.getBoardDetail(idx);
            if (board == null) {
               String url = "redirect:/board/list?type=" + type;
               return url;
            }
            model.addAttribute("board", board); // addAttribute 화면으로 데이터를 전달하는메소드
            System.out.println("board.getNoticeYn() : " + board.getNoticeYn());

         }
         model.addAttribute("type", type);
         
         List<CharacterDTO> charList = characterService.getCharacterList(type);
         CharacterDTO character = charList.get(charList.size() - 1);
         model.addAttribute("charName", character.getName());
         model.addAttribute("charTitle", character.getTitle());
         
         return "board/write";
      }
      System.out.println("type : " + type);
      String url = "redirect:/board/list?type=" + type;
      System.out.println("url : " + url);
      return url;
   }

   @PostMapping(value = "/board/register")
   public String registerBoard(RedirectAttributes redirectAttributes, HttpSession session, final BoardDTO params,
         Model model) {
      System.out.println("boardcontroller 입성");
      try {
         System.out.println("여기까지는 갈꺼야 ");
         System.out.println(params.getIdx());
         System.out.println(params.getContent());

         UserDTO user = (UserDTO) session.getAttribute("user");
         String myNickname = user.getNickname();
         params.setWriter(myNickname);
         boolean isRegistered = boardService.registerBoard(params);

         System.out.println("isregistered true" + isRegistered);
         if (isRegistered == false) {
            System.out.println("isregistered false" + isRegistered);
            return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list", Method.GET, null, model);
         }

      } catch (DataAccessException e) {

         return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);

      } catch (Exception e) {
         return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);
      }
      String url = "/board/list?type=" + params.getPostType();
      System.out.println("url 잘찍히는지 이게 안되서 당연히 그런거다." + url);
      return showMessageWithRedirect("게시글 등록이 완료되었습니다.", url, Method.GET, null, model);
   }

   @PostMapping(value = "/board/delete")
   public String deleteBoard(@RequestParam String type, @RequestParam(value = "idx", required = false) Long idx,
         Model model) {
      System.out.println("여기로 들어오나요?");
      if (idx == null) {
         return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
      }

      try {
         boolean isDeleted = boardService.deleteBoard(idx);
         if (isDeleted == false) {
            return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list", Method.GET, null, model);
         }
      } catch (DataAccessException e) {
         return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);

      } catch (Exception e) {
         return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);
      }
      String url = "/board/list?type=" + type;
      return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", url, Method.GET, null, model);
   }

   @GetMapping(value = "/board/mylist")
   public String openBoardMyList(HttpSession session, @ModelAttribute("params") BoardDTO params, Model model) {
      UserDTO user = (UserDTO) session.getAttribute("user");

      // user가 있을떄(로그인된 상태)
      if (user != null) {
         params.setWriter(user.getNickname());
         System.out.println("@@@@@@@@@params : " + params);
         List<BoardDTO> boardList = boardService.getBoardList(params);
         model.addAttribute("boardList", boardList);
         
         int listCount = boardList.size();
         model.addAttribute("listCount", listCount);
         System.out.println("@@@@@@@@@" + boardList);
         
         List<CharacterDTO> charList = new ArrayList<>();
         for(int i=0; i<listCount; i++) {
            List<CharacterDTO> tempList = characterService.getCharacterList(boardList.get(i).getPostType());
            CharacterDTO character = tempList.get(tempList.size() - 1);
            charList.add(character);
         }
         model.addAttribute("charList", charList);
      }
      return "board/mylist";
   }
}