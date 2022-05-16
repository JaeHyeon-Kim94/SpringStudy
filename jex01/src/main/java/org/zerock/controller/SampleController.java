package org.zerock.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import java.util.ArrayList;

@Controller
@RequestMapping("/sample/*")
@Log4j2
public class SampleController {

    @RequestMapping("")
    public void basic(){
        log.info("basic........................");
    }

    @RequestMapping(value="/basic", method={RequestMethod.GET, RequestMethod.POST})
    public void basicGet(){
        log.info("basic get, post");
    }

    @RequestMapping("/basicGetAndPost")
    public void basicGet2(){
        log.info("getMapping And postMapping");
    }
    //TODO 2022-05-16 타입 자동 변환
    //인자에 선언된 파라미터의 타입에 따라 requestparam을 자동으로 변환.
    @GetMapping("/ex01")
    public String ex01(SampleDTO dto, @RequestParam("agee") int agee){
        log.info(""+dto);
        log.info(agee);
        return "ex01";
    }
    //TODO 2022-05-16 객체 리스트 자동 변환
    //http://localhost:8080/sample/ex02Bean?DTOList%5B0%5D.name=aaa&DTOList%5B2%5D.name=bbb
    //인자에 선언된 타입의 변수명이 아니라, 해당 객체 멤버의 변수명을 사용하고 있음을 확인.
    //tomcat 버전에 따라 [] 사용시 invalid character 관련 exception 발생가능. uri encode 필요 -> [ : %5B ] : %5D
    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list){
        log.info("list dtos : " + list);
        return "ex02Bean";
    }


    //TODO 2022-05-16
    //변환이 가능한 데이터는 자동으로 변환되지만 경우에 따라서는 파라미터를 변환해서 처리해야 하는 경우 존재.
    //Date타입이 대표적인 예.
    //@InitBinder를 사용하거나 @DateTimeFormat을 적용
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//    }
    @GetMapping("/ex03")
    public String ex03(TodoDTO todo){
        log.info("todo: " + todo);
        return "ex03";
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, int page){
        log.info("dto: " + dto);
        log.info("page : " + page);

        return "/sample/ex04";
    }

    @GetMapping("/ex04_1")
    public String ex04_1(SampleDTO dto, @ModelAttribute("page") int page){
        log.info("dto: " + dto);
        log.info("page : " + page);

        return "/sample/ex04";
    }

    /*//TODO 2022-05-16 etc
        Servlet에서 redirect
         - response.sendRedirect("/home?name=aaa&age=10");

         스프링 MVC redirect
         RedirectAttributes 사용. Model처럼 파라미터로 선언하여 사용.
         redirectAttributes.addFlashAttribute("name", "AAA");
         RedirectAttributes.addFlashAttribute("age", 10);
    */

    //TODO
    //Controller의 리턴타입 : void, String, VO/DTO, ResponseEntity, Model/ModelAndView, HttpHeaders

    //1. void
    @GetMapping("/ex05_1")
    public void ex05(){
        log.info("go to /WEB-INF/views/sample/ex05.jsp");
    }

    //2. String
    //String타입에는 키워드 붙여서 사용 가능.
    //forward시 "forward:~"
    //redirect시 "redirect:~"
    @GetMapping("/ex05_2")
    public String ex05_2(){
        return "sample";
    }

    //3. VO/DTO
    //JSON 데이터 변환 및 반환을 위해선 jackson-databind 라이브러리 필요
    //@ResponseBody 어노테이션을 메서드 시그니처에 포함
    //MIME타입(Content-Type)이 application/json으로 처리됨.
    @GetMapping("/ex05_3")
    public @ResponseBody SampleDTO ex05_3(){
        log.info("ex05_3............");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("aaa");

        return dto;
    }
    //4. ResponseEntity (Http Headers)
    @GetMapping("/ex05_4")
    public ResponseEntity<String> ex07(){
        log.info("ex05_4..............");

        //스프링은 HttpServletRequest/Response를 직접 핸들링하지 않아도 되도록 하기때문에
        //헤더 정보나 데이터 전달을 위해 ResponseEntity를 사용할 수 있음.
        //ResponseEntity는 HttpHeaders 객체를 같이 전달할 수 있어 Http 헤더 메시지 가공이 가능.
        String msg = "{\"name\": \"홍길동\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    //5. fileupload
    //void 리턴 -> 동일한 jsp
    @GetMapping("/exUpload")
    public void exUpload(){
        log.info("/exUpload...........");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files){
        if(files == null) {
            log.info("파일없음---------------------");
            return;
        }
        files.forEach(file->{
            log.info("-------------------");
            log.info("name : " + file.getOriginalFilename());
            log.info("size : " + file.getSize());
        });
    }
}
