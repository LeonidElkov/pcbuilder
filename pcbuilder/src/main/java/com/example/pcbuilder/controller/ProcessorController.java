package com.example.pcbuilder.controller;

import com.example.pcbuilder.model.Processor;
import com.example.pcbuilder.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/processors") // Указывает базовый URL для всех методов контроллера
public class ProcessorController {

    @Autowired
    private ProcessorRepository processorRepository; // Репозиторий для работы с процессорами

    // Отображение списка процессоров
    @GetMapping
    public String listProcessors(Model model) {
        List<Processor> processors = processorRepository.findAll(); // Получаем список процессоров
        model.addAttribute("processors", processors); // Добавляем в модель
        return "processors"; // Возвращаем имя шаблона
    }

    // Отображение формы добавления нового процессора
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("processor", new Processor()); // Создаем новый объект процессора
        return "processor-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка добавления нового процессора
    @PostMapping
    public String addProcessor(@Validated @ModelAttribute Processor processor) {
        processorRepository.save(processor); // Сохраняем процессор в репозитории
        return "redirect:/processors"; // Перенаправляем на список процессоров
    }

    // Отображение формы редактирования процессора
    @GetMapping("/edit/{name}")
    public String showEditForm(@PathVariable String name, Model model) {
        Processor processor = processorRepository.findById(name)
                .orElseThrow(() -> new IllegalArgumentException("Invalid processor name: " + name)); // Ищем процессор по имени
        model.addAttribute("processor", processor); // Добавляем процессор в модель
        return "processor-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка обновления процессора
    @PostMapping("/{name}")
    public String updateProcessor(@PathVariable String name, @Validated @ModelAttribute Processor processor) {
        processor.setName(name); // Установка имени процессора
        processorRepository.save(processor); // Сохраняем изменения
        return "redirect:/processors"; // Перенаправляем на список процессоров
    }

    // Обработка удаления процессора
    @GetMapping("/delete/{name}")
    public String deleteProcessor(@PathVariable String name) {
        processorRepository.deleteById(name); // Удаляем процессор по имени
        return "redirect:/processors"; // Перенаправляем на список процессоров
    }
}
