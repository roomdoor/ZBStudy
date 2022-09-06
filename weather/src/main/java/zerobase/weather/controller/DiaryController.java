package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

	private final DiaryService diaryService;

	@ApiOperation(value = "diary 생성, 저장")
	@PostMapping("/create/diary")
	public void createDiary(
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
		@RequestBody String text
//            @RequestBody Memo memo
	) {
//        diaryService.createDiary(date, memo.getText());
		diaryService.createDiary(date, text);
	}

	@ApiOperation(value = "선택 date 의 모든 일기 가져옴")
	@GetMapping("/read/diary")
	public List<Diary> readDiary(
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "yyyy-mm-dd", example = "2022-09-05") LocalDate date) {
		return diaryService.readDiary(date);
	}

	@ApiOperation(value = "선택한 기간의 모든 일기 가져옴", notes = "시작 날짜, 끝 날짜 지정")
	@GetMapping("/read/diaries")
	public List<Diary> readDiaries(
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "싲가 날짜", example = "2022-09-05") LocalDate startDate,
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "끝 날짜", example = "2022-09-05") LocalDate endDate) {

		return diaryService.readDiaries(startDate, endDate);
	}

	@ApiOperation(value = "diary 업데이트")
	@PutMapping("/update/diary")
	public void updateDiary(
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
		@RequestBody String text
	) {
		diaryService.updateDiary(date, text);
	}

	@ApiOperation(value = "diary 삭제")
	@DeleteMapping("/delete/diary")
	public void deleteDiary(
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
	) {
		diaryService.deleteDiary(date);
	}
}