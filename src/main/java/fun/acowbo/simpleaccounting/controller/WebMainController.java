package fun.acowbo.simpleaccounting.controller;

import fun.acowbo.simpleaccounting.config.UserContext;
import fun.acowbo.simpleaccounting.service.ITbBillService;
import fun.acowbo.simpleaccounting.service.ITbCategoryService;
import fun.acowbo.simpleaccounting.util.TimeUtils;
import fun.acowbo.simpleaccounting.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 前端控制器
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @version 1.0
 * @since 2025/3/25
 */
@Controller
public class WebMainController {

    @Resource
    protected ITbBillService service;

    @Resource
    ITbCategoryService categoryService;

    @GetMapping("/index")
    public String home(Model model) {
        TimeVO allTimeType = TimeUtils.getAllTimeType();
        Long userId = UserContext.getUserId();
        DetailRespVO detail = service.getDetail(userId);
        model.addAttribute("count", service.getCount(userId));
        model.addAttribute("typeList", service.getTypeSum(userId));
        model.addAttribute("timeType", allTimeType);
        model.addAttribute("detail", detail);
        // 在首页控制器中
        model.addAttribute("activeTab", "index");
        return "index";
    }

    @GetMapping("/detail_account")
    public String detailAccount(@RequestParam("query") String query, Model model) {
        List<TbBillRespVO> billList = service.getBillList(new GetTbBillReqVO());
        if (!"''".equals(query)) {
            billList = billList.stream()
                    .filter(bill -> bill.getName().contains(query) || bill.getCategoryName().contains(query))
                    .collect(Collectors.toList());

        }
        // 在明细页控制器中
        model.addAttribute("activeTab", "detail");
        model.addAttribute("detailAccount", billList);
        return "detail-account";
    }

    @GetMapping("/get_type_sum_with_total")
    public String getTypeSumWithTotal(Model model) {
        model.addAttribute("activeTab", "getTypeSumWithTotal");
        return "get-type-sum-with-total";
    }

    @GetMapping("add_account")
    public String addAccount(Model model) {
        model.addAttribute("expenseForm", new SaveTbBillReqVO());
        model.addAttribute("categories", categoryService.getCategoryList(UserContext.getUserId()));
        return "add-account";
    }

    @PostMapping("/save_account")
    public String submitExpenseForm(@ModelAttribute SaveTbBillReqVO expenseForm) {
        // 处理表单数据
        // ...
        service.saveBill(expenseForm);
        // 重定向到成功页面或其他适当的页面
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String loginPage() {
        // 返回login.html页面
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }
}