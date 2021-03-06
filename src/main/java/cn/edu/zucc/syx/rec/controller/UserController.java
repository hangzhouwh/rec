package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.form.UserEditForm;
import cn.edu.zucc.syx.rec.form.UserForm;
import cn.edu.zucc.syx.rec.impl.UserServiceImpl;
import cn.edu.zucc.syx.rec.util.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "http://39.101.189.21:8888", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    private JsonUtil util = new JsonUtil();

    /**
     *  注册
     */ 
    @PostMapping("/register")
    public JSONObject register(@RequestBody UserForm userForm) {
        JSONObject ret = new JSONObject();

        // host已经存在
        if (userService.isUserExist(userForm.getHost()) == true) {
            ret.put("code", "error");
            ret.put("msg", "该用户已经存在");
        }

        // 默认name,随机
        if (userForm.getName() == null){
            userForm.setName(Tool.getRandomString(8));
        }

        User user = userService.create(userForm);
        ret = util.user2JSON(user);

        return ret;
    }

    /**
     * 登陆
     */
    @PostMapping("/login/{host}")
    public JSONObject login(@PathVariable("host") String host, String password){
        JSONObject ret = new JSONObject();

        if (userService.isUserExist(host) == false){
            ret.put("code", "error");
            ret.put("msg", "账号不存在");
            return ret;
        }

        User user = userService.login(host, password);
        if (user == null) {
            ret.put("code", "error");
            ret.put("msg", "密码错误");
            return ret;
        } else {
            ret = util.user2JSON(user);
        }

        return ret;
    }

    /**
     * 获取个人信息
     */
    @GetMapping("/{host}/get_personal_info")
    public JSONObject getPersonalInfo(@PathVariable("host") String host) {
        JSONObject ret = new JSONObject();
        User user = userService.queryUser(host);
        ret = util.userInfo2Json(user);
        return ret;
    }

    @PostMapping("/{host}/edit_pwd")
    public JSONObject editPwd(@PathVariable("host") String host, String pwd1, String pwd2){
        JSONObject ret = new JSONObject();
        if (!pwd1.equals(pwd2)){
            ret.put("code", "error");
            ret.put("msg", "");
        }

        User user = userService.editPwd(host, pwd1);
        ret = util.userInfo2Json(user);


        return ret;
    }

    /**
     * 修改个人信息
     */
    @PostMapping("/edit_personal_info")
    public JSONObject editInfo(@RequestBody UserEditForm userEditForm) {
        JSONObject ret = new JSONObject();
        User user = userService.editUser(userEditForm);

        ret = util.userInfo2Json(user);
        return ret;
    }

    /**
     * 添加播放记录
     */
    @PostMapping("/{host}/add_record")
    public JSONObject addRecord(@PathVariable String host,
                                @RequestParam("song_id") String songId){
        JSONObject ret = new JSONObject();
        RecordSong recordSong  = userService.addRecordSong(host, songId);
        ret = util.RecordSong2Json(recordSong);

        return ret;
    }

    /**
     * 查询用户所有推荐历史
     */
    @GetMapping("/{host}/list_record")
    public JSONObject listRecord(@PathVariable String host,@RequestParam("page_num") int pageNum,
                                 @RequestParam("page_size") int pageSize){
        JSONObject ret = new JSONObject();

        List<RecordSong> recordSongList  = userService.listRecordSongs(host);
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<RecordSong> page = PageUtil.createPageFromList(recordSongList, pageable);
        ret = util.usrRecordSongPage2Json(page);

        return ret;
    }

    @GetMapping("/{host}/delete_info")
    public JSONObject deleteInfo(@PathVariable String host){
        JSONObject ret = new JSONObject();
        User user = userService.deleteInfo(host);
        ret = util.userInfo2Json(user);
        return ret;
    }

    /**
     * 发送邮件，获取邮箱验证码
     */
    @PostMapping("/send_email")
    public JSONObject sendEmail(@RequestParam String address) throws EmailException {
        JSONObject ret = new JSONObject();

        EmailUtil emailUtil = new EmailUtil();
        String vc = emailUtil.sendEmail(address);
        ret.put("code", Statue.SUCCESS);
        JSONObject tmp = new JSONObject();
        tmp.put("vc", vc);
        ret.put("data", tmp);

        return ret;
    }

    /**
     * 发送邮件，获取邮箱验证码
     */
    @PostMapping("/send_msg")
    public JSONObject sendMsg(@RequestParam String phone) throws Exception {
        JSONObject ret = new JSONObject();

        MsgUtil msgUtil = new MsgUtil();
        String vc = msgUtil.send(phone);
        ret.put("code", Statue.SUCCESS);
        JSONObject tmp = new JSONObject();
        tmp.put("vc", vc);
        ret.put("data", tmp);

        return ret;
    }
}
