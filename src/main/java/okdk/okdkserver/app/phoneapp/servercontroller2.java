package okdk.okdkserver.app.phoneapp;

import lombok.RequiredArgsConstructor;
import okdk.okdkserver.app.keyosk.PythonSocet.PythonSocketForRegister;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:8080")
public class servercontroller2{

    @PostMapping(value = "/server/register",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String registerImage(@RequestParam MultipartFile imgFile) throws IOException {
        System.out.println(imgFile);
        String result = null;
        if (!imgFile.isEmpty()) {
            //path 설정 나중에하기
            String fullPath = "C:/OKDK_Backend/regimage/" + UUID.randomUUID()+imgFile.getOriginalFilename();
            System.out.println("파일 저장 full path = " + fullPath);
            imgFile.transferTo(new File(fullPath));
//            result=fullPath;


            //김지영이랑 통신하는 함수에서 추가해야 될것
            PythonSocketForRegister AIModel = new PythonSocketForRegister();
            result = AIModel.face_recognize_result(fullPath);

        }
        //인식 결과 내보내기
        return result;
    }
}
