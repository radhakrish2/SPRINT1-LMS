package com.learnhub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.CategoryDto;
import com.learnhub.dto.CourseDto;
import com.learnhub.dto.DashboardDto;
import com.learnhub.dto.UserDto;
import com.learnhub.service.CategoryService;
import com.learnhub.service.CourseService;
import com.learnhub.service.DashboardService;
import com.learnhub.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private final DashboardService dashboardService;
    private final UserService userService;
    private final CourseService courseService;
    private final CategoryService categoryService;

    // ================= Dashboard =================

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDto> getDashboard() {
        return ResponseEntity.ok(dashboardService.getAdminDashboard());
    }

    // ================= Users =================

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    // ================= Courses =================

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully.");
    }

    // ================= Categories =================

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto) {

        return ResponseEntity.ok(
                categoryService.createCategory(categoryDto));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDto categoryDto) {

        return ResponseEntity.ok(
                categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }

}