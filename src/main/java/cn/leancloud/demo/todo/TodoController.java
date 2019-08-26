package cn.leancloud.demo.todo;

import cn.leancloud.AVQuery;
import cn.leancloud.AVException;
import cn.leancloud.AVObject;

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
    AVQuery<Todo> query = AVObject.getQuery(Todo.class);
    query.orderByDescending("createdAt");
    query.include("createdAt");
    query.limit(limit);
    return new ModelAndView("todos/list", "todos", query.find());
  }

  @RequestMapping(path = "/", method = RequestMethod.POST)
  ModelAndView saveTodo(String content, RedirectAttributes redirectAttrs) throws AVException {
    Todo todo = new Todo();
    todo.setContent(content);
    todo.save();
    return new ModelAndView("redirect:/todos/");
  }

}
