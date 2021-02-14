package com.clone.insta.test;

import com.clone.insta.model.Follow; // ㅇㄴ.. ide 캐시 날리고 restart했더니 귀신같이 잘됨..
import com.clone.insta.model.Image;
import com.clone.insta.model.Likes;
import com.clone.insta.model.User;
import com.clone.insta.repository.ImageRepository;
import com.clone.insta.repository.UserRepository;
import com.clone.insta.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;

@Controller
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Value("${file.path}")
    private String fileRealPath;

    @GetMapping("/test/user/{id}")
    public @ResponseBody User testUser(@PathVariable int id){
        Optional<User> Ouser = userRepository.findById(id);
        User user = Ouser.get();

        return user;
    }

    @PostMapping("/test/image/uploadProc")
    public String testImageProc(@AuthenticationPrincipal MyUserDetail userDetail,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam("caption") String caption,
                                @RequestParam("location") String location,
                                @RequestParam("tags") String tags) throws IOException{
        // image upload 수행
        UUID uuid = UUID.randomUUID();
        String uuidFilename = uuid + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(fileRealPath + uuidFilename);

        if(!Files.exists(filePath)){
            Files.createFile(filePath);
        }

        // image 동기처리 하기
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(filePath, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate((int)file.getSize());
        buffer.put(file.getBytes());
        buffer.flip();

        Future<Integer> operation = fileChannel.write(buffer, 0);
        buffer.clear();

        while(!operation.isDone());

        return "redirect:/";
    }

    @GetMapping("/test/home")
    public String testHome() {
        return "home";
    }

    @GetMapping("/test/user")
    public @ResponseBody
    User getUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("cos");
        user.setName("홍길동");
        user.setEmail("cos@nate.com");
        user.setProfileImage("my.jpg");

        Image img1 = new Image();
        img1.setId(1);
        img1.setCaption("음식 사진");
        img1.setLocation("food.jpg");
        img1.setLocation("부산 서면");
        img1.setUser(user);

        Image img2 = new Image();
        img2.setId(2);
        img2.setCaption("장난감 사진");
        img2.setLocation("game.jpg");
        img2.setLocation("서울 강남");
        img2.setUser(user);

        List<Image> images = new ArrayList<>();
        images.add(img1);
        images.add(img2);
        user.setImages(images);

        return user;
    }

    @GetMapping("/test/image")
    public @ResponseBody
    Image getImage() {
        User user = new User();
        user.setId(1);
        user.setUsername("cos");
        user.setName("홍길동");
        user.setEmail("cos@nate.com");
        user.setProfileImage("my.jpg");

        Image img1 = new Image();
        img1.setId(1);
        img1.setCaption("음식 사진");
        img1.setLocation("food.jpg");
        img1.setLocation("부산 서면");
        img1.setUser(user);

        return img1;
    }

    @GetMapping("/test/images")
    public @ResponseBody
    List<Image> getImages() {
        User user = new User();
        user.setId(1);
        user.setUsername("cos");
        user.setName("홍길동");
        user.setEmail("cos@nate.com");
        user.setProfileImage("my.jpg");

        Image img1 = new Image();
        img1.setId(1);
        img1.setCaption("음식 사진");
        img1.setLocation("food.jpg");
        img1.setLocation("부산 서면");
        img1.setUser(user);

        Image img2 = new Image();
        img2.setId(2);
        img2.setCaption("장난감 사진");
        img2.setLocation("game.jpg");
        img2.setLocation("서울 강남");
        img2.setUser(user);

        List<Image> images = new ArrayList<>();
        images.add(img1);
        images.add(img2);
        user.setImages(images);

        return images;
    }

    @GetMapping("/test/like")
    public @ResponseBody
    Likes getLike() {
        User user = new User();
        user.setId(1);
        user.setUsername("cos");
        user.setName("홍길동");
        user.setEmail("cos@nate.com");
        user.setProfileImage("my.jpg");

        Image img1 = new Image();
        img1.setId(1);
        img1.setCaption("음식 사진");
        img1.setLocation("food.jpg");
        img1.setLocation("부산 서면");
        img1.setUser(user);

        Likes like = new Likes();
        like.setId(1);
        like.setUser(user);
        like.setImage(img1);

        return like;
    }

    @GetMapping("/test/follows")
    public @ResponseBody
    List<Follow> getFollows() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("cos");
        user1.setName("홍길동");
        user1.setEmail("cos@nate.com");
        user1.setProfileImage("my.jpg");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("ssar");
        user2.setName("장동건");
        user2.setEmail("ssar@nate.com");
        user2.setProfileImage("you.jpg");

        User user3 = new User();
        user3.setId(3);
        user3.setUsername("love");
        user3.setName("장보고");
        user3.setEmail("love@nate.com");
        user3.setProfileImage("love.jpg");

        Follow follow1 = new Follow();
        follow1.setId(1);
        follow1.setFromUser(user1);
        follow1.setToUser(user2);

        Follow follow2 = new Follow();
        follow2.setId(2);
        follow2.setFromUser(user1);
        follow2.setToUser(user3);

        Follow follow3 = new Follow();
        follow3.setId(3);
        follow3.setFromUser(user2);
        follow3.setToUser(user1);

        List<Follow> follows = new ArrayList<Follow>();
        follows.add(follow1);
        follows.add(follow2);
        follows.add(follow3);

        return follows;
    }

    @GetMapping("/test/login")
    public String testLogin() {
        return "auth/login";
    }

    @GetMapping("/test/join")
    public String testJoin() {
        return "auth/join";
    }


    @GetMapping("/test/profile")
    public String testProfile() {
        return "user/profile";
    }

    @GetMapping("/test/profileEdit")
    public String testProfileEdit() {
        return "user/profile_edit";
    }

    @GetMapping("/test/feed")
    public String testFeed() {
        return "image/feed";
    }

    @GetMapping("/test/imageUpload")
    public String testImageUpload() {
        return "image/image_upload";
    }

    @GetMapping("/test/explore")
    public String testExplore() {
        return "image/explore";
    }
}
