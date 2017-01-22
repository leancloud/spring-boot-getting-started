package cn.leancloud.demo.todo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.AVException;

@Controller
@RequestMapping("/todos")
public class TodoController {

  @RequestMapping(path = "/", method = RequestMethod.GET)
  ModelAndView listTodo(@RequestParam(required = false, defaultValue = "20") int limit)
      throws AVException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("limit", limit);
    List<Todo> todos = AVCloud.rpcFunction("listTodo", params);
    return new ModelAndView("todos/list", "todos", todos);
  }

  @RequestMapping(path = "/", method = RequestMethod.POST)
  ModelAndView saveTodo(String content, RedirectAttributes redirectAttrs) throws AVException {
    Todo todo = new Todo();
    todo.setContent(content);
    todo.save();
    return new ModelAndView("redirect:/todos/");
  }

}
