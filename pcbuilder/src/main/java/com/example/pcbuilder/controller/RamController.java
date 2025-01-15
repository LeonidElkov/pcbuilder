package com.example.pcbuilder.controller;

import com.example.pcbuilder.model.Ram;
import com.example.pcbuilder.repository.RamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ram") // Указывает базовый URL для всех методов контроллера
public class RamController {

    @Autowired
    private RamRepository ramRepository; // Репозиторий для работы с оперативной памятью

    // Получение всех модулей оперативной памяти
    @GetMapping
    public String getAllRam(Model model) {
        List<Ram> rams = ramRepository.findAll(); // Получаем список оперативной памяти
        model.addAttribute("ram", rams); // Добавляем в модель
        return "ram"; // Возвращаем имя шаблона
    }

    // Отображение формы для добавления нового модуля оперативной памяти
    @GetMapping("/add")
    public String addRam(Model model) {
        model.addAttribute("ram", new Ram()); // Создаем новый объект оперативной памяти
        return "ram-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка добавления нового модуля оперативной памяти
    @PostMapping
    public String createRam(@ModelAttribute Ram ram) {
        ramRepository.save(ram); // Сохраняем модуль оперативной памяти в репозитории
        return "redirect:/ram"; // Перенаправляем на список
    }

    // Отображение формы для редактирования модуля оперативной памяти
    @GetMapping("/edit/{name}")
    public String editRam(@PathVariable String name, Model model) {
        Ram ram = ramRepository.findById(name).orElse(null); // Ищем модуль оперативной памяти по имени
        if (ram == null) {
            return "redirect:/ram"; // Перенаправляем, если запись не найдена
        }
        model.addAttribute("ram", ram); // Добавляем модуль в модель
        return "ram-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка обновления модуля оперативной памяти
    @PostMapping("/edit/{name}") // Обработка обновления записи
    public String updateRam(@PathVariable String name, @ModelAttribute Ram ram) {
        ram.setName(name); // Устанавливаем имя, чтобы сохранить его
        ramRepository.save(ram); // Сохраняем изменения
        return "redirect:/ram"; // Перенаправляем на список
    }

    // Обработка удаления модуля оперативной памяти
    @GetMapping("/delete/{name}") // Изменено на @GetMapping
    public String deleteRam(@PathVariable String name) {
        ramRepository.deleteById(name); // Удаляем модуль по имени
        return "redirect:/ram"; // Перенаправляем на список
    }
}
