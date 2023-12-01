package com.example.Weather.controller;

import com.example.Weather.domain.Diary;
import com.example.Weather.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }
    @ApiOperation(value="일기 텍스트와 날짜를 이용해서 db에 일기 저장",notes = "여기에 말을 치면 어ㄷ디?")
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date, @RequestBody String text){

        diaryService.createDiary(date,text);
    }
    @ApiOperation(value="선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date){
//        if(date.isAfter(LocalDate.ofYearDay(2024,1))){
//            throw new InvalidDate();
//        }
        return diaryService.readDiary(date);
    }
    @ApiOperation(value="선택한 기간중의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(example="2020-02-02" ,value = "조회할 기간의 첫번째 날")
            LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(example="2020-02-02" ,value = "조회할 기간의 마지막 날")
            LocalDate endDate){

        return diaryService.readDiaries(startDate,endDate);

    }
    @ApiOperation(value="선택한 날짜의 첫번째 일기를 수정합니다.")
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(example="2020-02-02" ,value = "날짜 입력") LocalDate date,
            @RequestBody String text){
        diaryService.updateDiary(date,text);

    }
    @ApiOperation(value="선택한 일기 데이터를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        diaryService.delete(date);
    }
}
