package okdk.okdkserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.condition.Not.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class PythonImportTest {
    private String resolvePythonScriptPath(String path){
        File file = new File(path);
        return file.getAbsolutePath();
    }
    private List<String> readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }
    @Test
    public void givenPythonScript_whenPythonProcessInvoked_thenSuccess() throws IOException, InterruptedException{

        ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\user\\Desktop\\devlant\\hello.py", "test");
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        List<String> results = readProcessOutput(process.getInputStream());

        process.waitFor();

        System.out.println("results = " + results);

    }

}
