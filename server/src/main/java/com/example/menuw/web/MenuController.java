package com.example.menuw.web;

import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.requestDto.MenuRequestDto;
import com.example.menuw.dto.ResponseDto.ResponseDto;
import com.example.menuw.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PatchMapping("/like")
    public ResponseDto<Object> menuLike(@RequestBody MenuRequestDto menuRequestDto) {
        int dec = menuService.decMenu(menuRequestDto);
        if (dec==2) {
            return ResponseDto.res(HttpStatus.OK, "레시피 저장에 성공하였습니다.", Collections.emptyMap());
        }
        else {
            return ResponseDto.res(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다.", Collections.emptyMap());
        }
    }

    @GetMapping("/like/list")
    public ResponseDto<List<MenuDto>> getLikedMenus() {
        List<MenuDto> response = menuService.getLikedMenuList();
        return ResponseDto.res(HttpStatus.OK, "좋게 평가한 레시피 불러오기에 성공하였습니다.", response);
    }


}
