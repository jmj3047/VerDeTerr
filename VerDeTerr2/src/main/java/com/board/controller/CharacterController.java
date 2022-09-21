package com.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.CharacterDTO;
import com.board.domain.UserDTO;
import com.board.service.CharacterService;
import com.board.service.UserService;
import com.board.util.UiUtils;

@Controller
public class CharacterController extends UiUtils {

	@Autowired
	private CharacterService characterService;

	@GetMapping(value = "/character/list")
	public String openCharacterList(HttpSession session, Model model) {
		List<CharacterDTO> list = characterService.getCharacterList(null);
		int listCount = list.size();
		System.out.println(listCount);
		
		List<List<CharacterDTO>> characterList = new ArrayList<>();
		CharacterDTO noCharacter = new CharacterDTO();
		for(int i=0; i<listCount; i++) {
			if(i%4==0) {
				List<CharacterDTO> rows = new ArrayList<>();
				rows.add(list.get(i));
				if(i+1>=listCount) {
					rows.add(noCharacter);
					rows.add(noCharacter);
					rows.add(noCharacter);
					characterList.add(rows);
					break;
				}
				rows.add(list.get(i+1));
				if(i+2>=listCount) {
					rows.add(noCharacter);
					rows.add(noCharacter);
					characterList.add(rows);
					break;
				}
				rows.add(list.get(i+2));
				if(i+3>=listCount) {
					rows.add(noCharacter);
					characterList.add(rows);
					break;
				}
				rows.add(list.get(i+3));
				characterList.add(rows);
			}
		}
		System.out.println(characterList);
		model.addAttribute("characterList", characterList);
		model.addAttribute("listCount", listCount);

		UserDTO user = (UserDTO) session.getAttribute("user");
		if(user!=null) {
			boolean isManager = user.isManagerYn();
			model.addAttribute("isManager", isManager);
		}
		return "character/list";
	};

	// 게시글 내용 보기
	@GetMapping(value = "/character/view")
	public String openCharacterDetail(HttpSession session, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return "redirect:/character/list";
		}

		CharacterDTO character = characterService.getCharacterDetail(idx);
		if (character == null) {

			return "redirect:/character/list";
		}
		System.out.println("character : " + character);
		model.addAttribute("character", character);

		UserDTO user = (UserDTO) session.getAttribute("user");
		if(user!=null) {
			if(user.isManagerYn()) {
				model.addAttribute("isManager", "true");
			}
		}

		return "character/view";
	}


	// 캐릭터 등록 & 수정 페이지
	@GetMapping("/character/write")
	public String openCharacterWrite(@RequestParam(value = "idx", required = false) Long idx, Model model,
			HttpSession session) {
		CharacterDTO character = new CharacterDTO();
		if (idx != null) {
			character = characterService.getCharacterDetail(idx);
			System.out.println(character);
			if (character == null) {
				return "redirect:/character/list";
			}
		}
		model.addAttribute("character", character);
		return "character/write";
	}

	// 신규 캐릭터 등록
	@PostMapping("/character/register")
	public String saveCharacter(@RequestParam(value = "idx", required = false) Long idx, CharacterDTO params,
			@RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("***** idx : " + idx + ", params : " + params);
		System.out.println("***** file : " + file);
		String projectpath = System.getProperty("user.dir") + "/src/main/resources/static/assets/img/character"; // user.dir은 프로젝트 경로를 담아줌
		UUID uuid = UUID.randomUUID(); // 랜덤으로 이름 생성
		if (!file.getOriginalFilename().isEmpty()) {
			String filename = uuid + "_" + file.getOriginalFilename(); // 파일 이름은 UUID에 있는 랜덤값 + 원래 파일 이름으로 설정된다.
			File saveFile = new File(projectpath, filename); // 위에 적힌 경로에 name으로 저장
			file.transferTo(saveFile);
			params.setFilename(filename);
			params.setFilepath("/assets/img/character/" + filename);
		}
		if (idx == null) {
			characterService.registerCharacter(params);
			redirectAttributes.addFlashAttribute("msgChar", "캐릭터 등록이 완료되었습니다.");
		} else {
			characterService.updateCharacter(params);
			redirectAttributes.addFlashAttribute("msgChar", "캐릭터 수정이 완료되었습니다.");
		}
		return "redirect:/character/list";
	}
	// 게시글 삭제하기
	@PostMapping("/character/delete")
	public String deleteCharacterList(@RequestParam(value = "idx", required = false) Long idx) {
		characterService.deleteCharacter(idx);
		return "redirect:/character/list";
	}

}
