package com.example.WorkoutSite.controller;


import com.example.WorkoutSite.WorkoutSiteApplication;
import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.model.WorkOut;
import com.example.WorkoutSite.service.UserService;
import com.example.WorkoutSite.service.WorkOutService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WorkoutSiteApplication.class)
@WebMvcTest(value = WorkOutController.class, secure = false)
public class WorkOutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkOutController workOutController;

    @MockBean
    private WorkOutService workOutService;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.workOutController).build();
        MockitoAnnotations.initMocks(this);
    }

    User demoUser = new User(1, "password", "userName","userEmailId");
    WorkOut demoWorkout= new WorkOut(1, (double)123, "Cycling", demoUser);

    @Test
    public void createWorkOutTest() throws Exception{
        given(workOutService.createWorkout(Mockito.any(WorkOut.class))).willReturn(demoWorkout);
        String content = new Gson().toJson(demoWorkout);
        ResultActions result = mockMvc
                .perform(post("/workout").content(content).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        assertThat(result.andReturn().equals(demoWorkout));
    }

    @Test
    public void getWorkOutList() throws Exception{
        List<WorkOut> workOutList = new ArrayList<WorkOut>();
        workOutList.add(demoWorkout);
        given(workOutService.getWorkoutListByUserId(1)).willReturn(workOutList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/workout/ofUser/"+demoWorkout.getUser().getUserId()).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertThat(result.getResponse().equals(workOutList));


        }

}
