package test.task.TestForInterview.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import test.task.TestForInterview.model.FilesInfo;
import test.task.TestForInterview.service.FilesDataService;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FilesDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilesDataService filesDataService;

    /*
     * Test that checking for correct method execution
     */
    @Test
    public void getCorrectFilesInfo() throws Exception {
        FilesInfo filesInfo = new FilesInfo();
        filesInfo.setAmountOfDocuments(4);
        filesInfo.setAmountOfPages(323);
        Mockito.when(filesDataService.getFilesInfo("somePath")).thenReturn(filesInfo);
        mockMvc.perform(get("/getInfo/?path=somePath")).andExpect(status().isOk())
                .andExpect(jsonPath("Documents", Matchers.equalTo(4)))
                .andExpect(jsonPath("Pages", Matchers.equalTo(323)));
    }
    /*
     * Unit test that checking for NPE if wrong Path passed
     */
    @Test
    public void getFilesInfoNpe() {
        assertThrows(NullPointerException.class,
                ()->{
                    FilesDataController controller = new FilesDataController();
                    FilesInfo filesInfo = controller.getFilesInfo("Default");
                });
    }

}