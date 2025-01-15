package com.example.pcbuilder.controller;

import com.example.pcbuilder.model.Gpu;
import com.example.pcbuilder.repository.GpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gpus") // Указывает базовый URL для всех методов контроллера
public class GpuController {

    @Autowired
    private GpuRepository gpuRepository; // Репозиторий для работы с видеокартами

    // Отображение списка видеокарт
    @GetMapping
    public String listGpus(Model model) {
        List<Gpu> gpus = gpuRepository.findAll(); // Получаем список видеокарт
        model.addAttribute("gpus", gpus); // Добавляем в модель
        return "gpus"; // Возвращаем имя шаблона
    }

    // Отображение формы для добавления новой видеокарты
    @GetMapping("/add")
    public String addGpu(Model model) {
        model.addAttribute("gpu", new Gpu()); // Создаем новый объект видеокарты
        return "gpu-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка добавления новой видеокарты
    @PostMapping
    public String createGpu(@ModelAttribute Gpu gpu) {
        gpuRepository.save(gpu); // Сохраняем видеокарту в репозитории
        return "redirect:/gpus"; // Перенаправляем на список видеокарт
    }

    // Отображение формы для редактирования видеокарты
    @GetMapping("/edit/{name}")
    public String editGpu(@PathVariable String name, Model model) {
        Gpu gpu = gpuRepository.findById(name).orElse(null); // Ищем видеокарту по имени
        if (gpu == null) {
            return "redirect:/gpus"; // Перенаправляем, если видеокарта не найдена
        }
        model.addAttribute("gpu", gpu); // Добавляем видеокарту в модель
        return "gpu-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка обновления видеокарты
    @PostMapping("/{name}")
    public String updateGpu(@PathVariable String name, @ModelAttribute Gpu gpu) {
        gpu.setName(name); // Убедимся, что имя не меняется
        gpuRepository.save(gpu); // Сохраняем изменения
        return "redirect:/gpus"; // Перенаправляем на список видеокарт
    }

    // Обработка удаления видеокарты
    @GetMapping("/delete/{name}")
    public String deleteGpu(@PathVariable String name) {
        gpuRepository.deleteById(name); // Удаляем видеокарту по имени
        return "redirect:/gpus"; // Перенаправляем на список видеокарт
    }
}
