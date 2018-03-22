package com.example.WorkoutSite.controller;


import com.example.WorkoutSite.WorkoutSiteApplication;
import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.model.WorkOut;
import com.example.WorkoutSite.model.WorkOutTransaction;
import com.example.WorkoutSite.service.WorkOutTransactionService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WorkoutSiteApplication.class)
@WebMvcTest(value = WorkOutTransactionController.class, secure = false)
public class WorkOutTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkOutTransactionController workOutTransactionController;

    @MockBean
    private  WorkOutTransactionService workOutTransactionService;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.workOutTransactionController).build();
        MockitoAnnotations.initMocks(this);
    }
    User demoUser = new User(1, "password", "userName","userEmailId");
    WorkOut demoWorkout= new WorkOut(1, (double)123, "Cycling", demoUser);
    WorkOutTransaction demoTransaction = new WorkOutTransaction(1,demoWorkout,LocalDateTime.now(), LocalDateTime.now() );


    @Test
    public void createTransactionTest() throws Exception{
        given(workOutTransactionService.defineWorkOutTransaction(Mockito.any(WorkOutTransaction.class))).willReturn(demoTransaction);
        String content = new Gson().toJson(demoTransaction);
        mockMvc.perform(post("/workOutTransaction").content(content).contentType(APPLICATION_JSON_UTF8)).andReturn();
    }

    @Test
    public void getTransactionListTest() throws Exception{
        List<WorkOutTransaction> workOutTransactionList = new ArrayList<WorkOutTransaction>();
        workOutTransactionList.add(demoTransaction);
        given(workOutTransactionService.getWorkOutTransactionList(1)).willReturn(workOutTransactionList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/workout/ofWorkout/"+demoTransaction.getWorkOut().getWorkoutId()).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertThat(result.getResponse().equals(workOutTransactionList));
    }
}
