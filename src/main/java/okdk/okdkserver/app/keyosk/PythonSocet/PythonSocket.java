package okdk.okdkserver.app.keyosk.PythonSocet;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class PythonSocket {
    protected String resolvePythonScriptPath(String path){
        File file = new File(path);
        return file.getAbsolutePath();
    }
    protected List<String> readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines().collect(Collectors.toList());
        }
    }
}
