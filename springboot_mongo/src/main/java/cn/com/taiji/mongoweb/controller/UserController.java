package cn.com.taiji.mongoweb.controller;

import cn.com.taiji.mongoweb.model.User;
import cn.com.taiji.mongoweb.service.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 入口
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/get/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		userService.remove(id);
		return "delete sucess";
	}

	@RequestMapping("/add")
	public String insert() {
		User user = new User(16, "" + 16, 16);
		userService.insert(user);
		return "sucess";
	}

	@RequestMapping("/insert")
	public String insertAll() {
		List<User> list = new ArrayList<>();
		for (int i = 10; i < 15; i++) {
			list.add(new User(i, "" + i, i));
		}
		userService.insertAll(list);
		return "sucess";
	}

	@RequestMapping("/find/all")
	public List<User> find() {
		return userService.findAll();
	}

	@RequestMapping("/find/{start}")
	public List<User> findByPage(@PathVariable int start, User user) {
		Pageable pageable = new PageRequest(start, 2);
		return userService.findByPage(user, pageable);
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id) {
		User user = new User(id, "" + 1, 1);
		userService.update(user);
		return "sucess";
	}

	@RequestMapping("/geo")
	public List<DBObject> geo() {
		DBObject query = new BasicDBObject();
		Point point = new Point(118.783799, 31.979234);
		// point.setLng(118.783799);
		// point.setLat(31.979234);
		int limit = 5;
		Long maxDistance = 5000L; // 米
		List<DBObject> list = userService.geo("point.test", query, point, limit, maxDistance);
		for (DBObject obj : list)
			System.out.println(obj);
		return list;
	}

	@RequestMapping("/withinPolygon")
	public Set<DBObject> withinPolygon() {
		DBObject query = new BasicDBObject();
		DBObject field = new BasicDBObject();
		int limit = 3;//
		List<double[]> square = new ArrayList<>();
		square.add(new double[] { 110, 40 });
		square.add(new double[] { 110, 0 });
		square.add(new double[] { 120, 0 });
		square.add(new double[] { 120, 40 });
		square.add(new double[] { 110, 40 });
		List<double[]> triangle = new ArrayList<>();
		triangle.add(new double[] { 80, 50 });
		triangle.add(new double[] { 60, 40 });
		triangle.add(new double[] { 60, 60 });
		triangle.add(new double[] { 80, 50 });
		List<DBObject> listSquare = userService.withinPolygon("point.test", "loc", square, field, query, limit);
		List<DBObject> listTriangle = userService.withinPolygon("point.test", "loc", triangle, field, query, limit);
		Set<DBObject> set = new TreeSet<DBObject>();
		for (DBObject obj : listSquare)
			set.add(obj);
		for (DBObject obj : listTriangle)
			set.add(obj);
		return set;
	}

}
