package com.example.pcbuilder.controller;

import com.example.pcbuilder.model.Motherboard;
import com.example.pcbuilder.repository.MotherboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motherboards") // Указывает базовый URL для всех методов контроллера
public class MotherboardController {

    @Autowired
    private MotherboardRepository motherboardRepository; // Репозиторий для работы с материнскими платами

    // Получение всех материнских плат
    @GetMapping
    public String getAllMotherboards(Model model) {
        List<Motherboard> motherboards = motherboardRepository.findAll(); // Получаем список материнских плат
        model.addAttribute("motherboards", motherboards); // Добавляем в модель
        return "motherboards"; // Возвращаем имя шаблона
    }

    // Отображение формы для добавления новой материнской платы
    @GetMapping("/add")
    public String addMotherboard(Model model) {
        model.addAttribute("motherboard", new Motherboard()); // Создаем новый объект материнской платы
        return "motherboard-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка добавления новой материнской платы
    @PostMapping
    public String createMotherboard(@ModelAttribute Motherboard motherboard) {
        motherboardRepository.save(motherboard); // Сохраняем материнскую плату в репозитории
        return "redirect:/motherboards"; // Перенаправляем на список материнских плат
    }

    // Отображение формы для редактирования материнской платы
    @GetMapping("/edit/{name}")
    public String editMotherboard(@PathVariable String name, Model model) {
        Motherboard motherboard = motherboardRepository.findById(name).orElse(null); // Ищем материнскую плату по имени
        if (motherboard == null) {
            return "redirect:/motherboards"; // Перенаправляем, если материнская плата не найдена
        }
        model.addAttribute("motherboard", motherboard); // Добавляем материнскую плату в модель
        return "motherboard-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка обновления материнской платы
    @PostMapping("/edit/{name}") // Обработка обновления записи
    public String updateMotherboard(@PathVariable String name, @ModelAttribute Motherboard motherboard) {
        motherboard.setName(name); // Устанавливаем имя, чтобы сохранить его
        motherboardRepository.save(motherboard); // Сохраняем изменения
        return "redirect:/motherboards"; // Перенаправляем на список материнских плат
    }

    // Обработка удаления материнской платы
    @GetMapping("/delete/{name}")
    public String deleteMotherboard(@PathVariable String name) {
        motherboardRepository.deleteById(name); // Удаляем материнскую плату по имени
        return "redirect:/motherboards"; // Перенаправляем на список материнских плат
    }
}
