package cn.leancloud.demo.todo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avos.avoscloud.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todos")
public class TodoController {

  @RequestMapping(path = "/", method = RequestMethod.GET)
  ModelAndView listTodo(@RequestParam(required = false, defaultValue = "20") int limit) throws AVException {
    try {
      AVQuery<Todo> query = AVObject.getQuery(Todo.class);
      query.orderByDescending("createdAt");
      query.include("createdAt");
      query.limit(limit);
      return new ModelAndView("todos/list", "todos", query.find());

    } catch (AVException e) {
      if (e.getCode() == 101) {
        // 该错误的信息为：{ code: 101, message: 'Class or object doesn\'t exists.' }，说明 Todo
        // 数据表还未创建，所以返回空的 Todo 列表。
        // 具体的错误代码详见：https://leancloud.cn/docs/error_code.html
        return new ModelAndView("todos/list", "todos", Collections.emptyList());
      }
      throw e;
    }
  }

  @RequestMapping(path = "/", method = RequestMethod.POST)
  ModelAndView saveTodo(String content, RedirectAttributes redirectAttrs) throws AVException {
    Todo todo = new Todo();
    todo.setContent(content);
    todo.save();
    return new ModelAndView("redirect:/todos/");
  }

}
