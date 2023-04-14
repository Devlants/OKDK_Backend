package okdk.okdkserver.app.keyosk;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import okdk.okdkserver.app.keyosk.PythonSocet.PythonSocketForRecognization;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class servercontroller {

    @PostMapping(value = "/server/recog",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String recognizeImage(@RequestParam MultipartFile imgFile) throws IOException {
        System.out.println(imgFile);
        String result = null;
        if (!imgFile.isEmpty()) {
            //path 설정 나중에하기
            String fullPath = "C:/OKDK_Backend/recogimage/" + UUID.randomUUID()+imgFile.getOriginalFilename();
//            result=fullPath;
            System.out.println("파일 저장 full path = " + fullPath);
            imgFile.transferTo(new File(fullPath));

//            모델로 이미지 경로 보내기
            PythonSocketForRecognization AIModel = new PythonSocketForRecognization();
            result = AIModel.face_recognize_result(fullPath);


        }
        //인식 결과 내보내기
        return result;
    }

    @PostMapping(value = "/arduino/json")
    public void arduninoSignal(@RequestBody arduinoRequest ArduinoRequest){
        System.out.println(ArduinoRequest.resultvalue);
    }
}
