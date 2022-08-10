package com.cy.store.controller;

import com.cy.store.bean.Address;
import com.cy.store.service.AddressService;
import com.cy.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author joker
 * @create 2022-04-27
 */
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{

    @Autowired
    private AddressService addressService;

    @PostMapping("add_new_address")
    public JsonResult<Void> add(Address address, HttpSession session){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        addressService.addAddress(uid,username,address);

        return new JsonResult<>(OK);
    }
    @GetMapping("")
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK, data);
    }

    /**
     * restful风格的请求
     * @param aid
     * @param session
     * @return
     */
    @RequestMapping("set_default/{aid}")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.updateDefault(uid, aid, username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("delete/{aid}")
    public JsonResult<Void> delete(@PathVariable("aid")Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.delete(aid, uid, username);
        return new JsonResult<Void>(OK);
    }
}
