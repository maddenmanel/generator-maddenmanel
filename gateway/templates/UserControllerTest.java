package <%=packageName%>.<%=baseName%>.controller;

import <%=packageName%>.<%=baseName%>.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

public class UserControllerTest extends BaseTest {
  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    saveUsers();
  }

  private void saveUsers() {
    for (int i=1;i<10;i++){
      final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("id",""+i);
      params.add("name", "name"+i);
      params.add("age", ""+i);
      params.add("addr", "addr"+i);
      try {
        MvcResult mvcResult=  mockMvc.perform(MockMvcRequestBuilders.post("/user")
          .params(params)).andReturn();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void getMessage() throws Exception {
    String mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/message/6"))
      .andReturn().getResponse().getContentAsString();
    System.out.println("Result === "+mvcResult);
  }

}
