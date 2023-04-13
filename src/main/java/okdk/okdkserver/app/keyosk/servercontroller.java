package okdk.okdkserver.app.keyosk;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import okdk.okdkserver.app.keyosk.PythonSocet.PythonSocketForRecognization;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class servercontroller {

    @PostMapping(value = "/server/image",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveImage(@RequestParam MultipartFile imgFile) throws IOException {
        System.out.println(imgFile);
        String result = null;
        if (!imgFile.isEmpty()) {
            String fullPath = "C:/okdkserver/image/" + imgFile.getOriginalFilename();
            System.out.println("파일 저장 full path = " + fullPath);
            imgFile.transferTo(new File(fullPath));

            //모델로 이미지 경로 보내기
            PythonSocketForRecognization AIModel = new PythonSocketForRecognization();
            result = AIModel.face_recognize_result(fullPath);

            //김지영이랑 통신하는 함수에서 추가해야 될것
            //PythonSocketForRegister AIModel = new PythonSocketForRegister();
            //result = AIModel.face_recognize_result(fullPath);

        }
        //인식 결과 내보내기
        return result;
    }

    @PostMapping(value = "/arduino/json")
    public void arduninoSignal(@RequestBody arduinoRequest ArduinoRequest){
        System.out.println(ArduinoRequest.resultvalue);
    }
}
