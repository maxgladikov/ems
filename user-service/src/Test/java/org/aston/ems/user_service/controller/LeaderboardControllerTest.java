//package org.aston.ems.user_service.controller;
//
//
//import org.aston.ems.user_service.service.RatingStudentService;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@Nested
//@WebMvcTest(LeaderboardController.class)
//class LeaderboardControllerTest {
//
//
//    @MockBean
//    LeaderboardController leaderboardController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    RatingStudentService ratingStudentService;
//
//    private final String URI_IS_OK_BEST = "http://localhost:9003/api/v1/leaderboard/top?start=0&end=3";
//    private final String URI_IS_BAD_BEST = "http://localhost:9003/api/v1/leaderboard/top";
//
//    private final String URI_IS_OK_WORST = "http://localhost:9003/api/v1/leaderboard/worst?start=0&end=3";
//
//    private final String URI_IS_BAD_WORST = "http://localhost:9003/api/v1/leaderboard/worst";
//    @Test
//    void getRatingBestStudentIsOkURI() throws Exception {
//
//        mockMvc.perform(get(URI_IS_OK_BEST))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getRatingBestStudentIsBadURI() throws Exception {
//
//        mockMvc.perform(get(URI_IS_BAD_BEST))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void getRatingWorstStudentIsOkURI() throws Exception {
//
//        mockMvc.perform(get(URI_IS_OK_WORST))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getRatingWorstStudentIsBadURI() throws Exception {
//
//        mockMvc.perform(get(URI_IS_BAD_WORST))
//                .andExpect(status().isBadRequest());
//    }
//}
//
//
//
//
//
