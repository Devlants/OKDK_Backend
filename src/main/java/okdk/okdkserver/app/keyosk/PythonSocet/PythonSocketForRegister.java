package okdk.okdkserver.app.keyosk.PythonSocet;

import java.io.IOException;

public class PythonSocketForRegister extends PythonSocket{
    public String face_recognize_result(String imagePath) throws IOException {

        ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\user\\Desktop\\devlant\\hello.py", imagePath);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        String results = readProcessOutput(process.getInputStream()).toString();

        return results;

    }
}
