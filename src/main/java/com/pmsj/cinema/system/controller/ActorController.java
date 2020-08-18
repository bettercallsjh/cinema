package com.pmsj.cinema.system.controller;

import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.common.entity.Cast;
import com.pmsj.cinema.system.service.CastService;
import com.pmsj.cinema.system.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/12 16:06
 **/
@Controller
@RequestMapping("actor")
public class ActorController {

    @Autowired
    CastService castService;

    @RequestMapping("getAllActor")
    @ResponseBody
    public ReturnUtil getAllActor(Integer page, Integer limit) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (limit == null || limit == 0) {
            limit = 10;
        }

        PageInfo<Cast> allCastByPage = castService.getAllCastByPage(page, limit);
        ReturnUtil returnUtil = new ReturnUtil(0, "success", allCastByPage.getTotal(), allCastByPage.getList());
        return returnUtil;
    }

    @RequestMapping("addImg")
    @ResponseBody
    public ReturnUtil addImg(HttpServletRequest request, MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String substring = "";
        if (fileName.lastIndexOf(".") != -1) {
            substring = fileName.substring(fileName.lastIndexOf("."));
        }
        String name = UUID.randomUUID().toString().replaceAll("-", "") + substring;
        request.getServletPath();

        String property = System.getProperty("user.dir");
        String realPath = property + "\\src\\main\\resources\\static\\img";
        File fileMkdir = new File(realPath);

        if (!fileMkdir.exists()) {
            fileMkdir.mkdir();
        }

        FileOutputStream os = new FileOutputStream(fileMkdir.getPath() + "\\" + name);
        InputStream in = file.getInputStream();
        int b = 0;
        while ((b = in.read()) != -1) { //读取文件
            os.write(b);
        }
        os.flush(); //关闭流
        in.close();
        os.close();

        ReturnUtil returnUtil = new ReturnUtil();
        returnUtil.setCode(0);
        returnUtil.setMsg("/img/" + name);
        return returnUtil;
    }

    @RequestMapping("addAcotr")
    public void addAcotr(Cast cast) {
        castService.add(cast);
    }
}
