package com.rest.controller;

import com.rest.Request.AddContentRequest;
import com.rest.converter.ContentConverter;
import com.rest.domain.Content;
import com.rest.domain.ContentTime;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Controller
public class ContentAddController {
    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ContentTimeMapper contentTimeMapper;

    @RequestMapping("/addContent")
    @ResponseBody
    public boolean addContent(AddContentRequest request){
        //which shall redirect when ok.
        Calendar calendar = Calendar.getInstance();
        Content content = ContentConverter.convertToContent(request);
        contentMapper.addContent(content);
        ContentTime time = new ContentTime();
        time.setYear(calendar.get(Calendar.YEAR));
        time.setMonth(calendar.get(Calendar.MONTH)+1);
        time.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        time.setContent_id(content.getId());
        contentTimeMapper.insert(time);
        return true;
    }

    @RequestMapping("/add")
    public String addPage(){
        return "add";
    }
}
