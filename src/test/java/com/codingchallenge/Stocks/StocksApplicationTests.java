package com.codingchallenge.Stocks;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@RunWith(SpringJUnit4ClassRunner.class)
class StocksApplicationTests {

    @Autowired
    private MockMvc mockMvc;

	@MockBean
	private StockService stockService;

	@MockBean
	private StockRepository stockRepository;

	@Test
	public void testSaveStockRecord() throws Exception {
		StockRecord sr = new StockRecord(500L, "2", "AA", "1/14/2011", "$16.71", "$16.71",
				"$15.64", "$15.97", "242963398", "-4.42849", "1.380223028", "239655616", "$16.19", "$15.79", "-2.47066", "19", "0.187852");

		mockMvc.perform(MockMvcRequestBuilders.post("/addStock").content(TestUtil.objectToJson(sr))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetStockRecords() throws Exception {
		List<StockRecord> list = new ArrayList<>();
		StockRecord sr = new StockRecord(500L, "2", "AA", "1/14/2011", "$16.71", "$16.71",
				"$15.64", "$15.97", "242963398", "-4.42849", "1.380223028", "239655616",
				"$16.19", "$15.79", "-2.47066", "19", "0.187852");
		StockRecord sr1 = new StockRecord(501L, "1", "AA", "2/25/2011", "$16.98", "$17.15", "$15.96", "$16.68",
				"132981863", "-1.76678", "66.17769355", "80023895", "$16.81", "$16.58",
				"-1.36823","76", "0.179856");
		list.add(sr);
		list.add(sr1);

		ResponseEntity<List<StockRecord>> re = new ResponseEntity<>(list, HttpStatus.OK);

		Mockito.when(stockService.getStocks("AA")).thenReturn(re);
		mockMvc.perform(MockMvcRequestBuilders.get("/stockRecords/AA")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) /*.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].stock", Matchers.equalTo("AA")))
				.andExpect(jsonPath("$[1].stock", Matchers.equalTo("AA")))  */;
	}

	@Test
	public void testUploadBulkRecords() throws Exception {
		List<StockRecord> list = new ArrayList<>();
		StockRecord sr = new StockRecord(500L, "2", "AA", "1/14/2011", "$16.71", "$16.71",
				"$15.64", "$15.97", "242963398", "-4.42849", "1.380223028", "239655616",
				"$16.19", "$15.79", "-2.47066", "19", "0.187852");
		StockRecord sr1 = new StockRecord(501L, "1", "AA", "2/25/2011", "$16.98", "$17.15", "$15.96", "$16.68",
				"132981863", "-1.76678", "66.17769355", "80023895", "$16.81", "$16.58",
				"-1.36823","76", "0.179856");
		list.add(sr);
		list.add(sr1);
		Mockito.when(stockRepository.saveAll(list)).thenReturn(list);

	/*	MockMultipartFile file
				= new MockMultipartFile(
				"file",
				"hello.txt",
				MediaType.TEXT_PLAIN_VALUE,
				"Hello, World!".getBytes()
		);
		mockMvc.perform(MockMvcRequestBuilders.post("/stockRecords/bulkUpload")
				.file(file))
				.andExpect(status().isOk());

		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
		mockMvc.perform(fileUpload("/api/files").file(file))
				.andDo(print())
				.andExpect(status().isCreated());   */
	}

}
