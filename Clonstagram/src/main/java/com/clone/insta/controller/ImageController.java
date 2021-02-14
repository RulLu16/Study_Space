package com.clone.insta.controller;

import com.clone.insta.model.Image;
import com.clone.insta.model.Tag;
import com.clone.insta.model.User;
import com.clone.insta.util.Utils;
import com.clone.insta.repository.ImageRepository;
import com.clone.insta.repository.TagRepository;
import com.clone.insta.service.MyUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Value("${file.path}")
    private String fileRealPath;

    @Autowired // 필드 자동주입 나중에 생성자 자동주입으로 바꿔봐야될듯..
    private ImageRepository imageRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping({"/","/image/feed"})
    public String imageFeed(@AuthenticationPrincipal MyUserDetail userDetail){
        log.info("username: " + userDetail.getUsername());

        return "image/feed";
    }

    @GetMapping("/image/upload")
    public String imageUpload(){
        return "image/image_upload";
    }

    @PostMapping("/image/uploadProc")
    public String imageUploadProc(
            @AuthenticationPrincipal MyUserDetail userDetail,
            @RequestParam("file") MultipartFile file,
            @RequestParam("caption") String caption,
            @RequestParam("location") String location,
            @RequestParam("tags") String tags){

        UUID uuid = UUID.randomUUID();
        String uuidFilename = uuid + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(fileRealPath + uuidFilename);

        try{
            Files.write(filePath, file.getBytes()); // hard disk history
        }catch(IOException e){
            e.printStackTrace();
        }

        User principal = userDetail.getUser();

        Image image = new Image();
        image.setCaption(caption);
        image.setLocation(location);
        image.setUser(principal);
        image.setPostImage(uuidFilename);

        imageRepository.save(image);

        List<String> tagList = Utils.tagParser(tags);

        for(String tag : tagList){
            Tag t = new Tag();
            t.setImage(image);
            t.setName(tag);

            tagRepository.save(t);
            image.getTags().add(t);
        }

        return "redirect:/";
    }
}
