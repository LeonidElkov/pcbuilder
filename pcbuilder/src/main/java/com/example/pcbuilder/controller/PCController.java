package com.example.pcbuilder.controller;

import com.example.pcbuilder.model.PC;
import com.example.pcbuilder.repository.PCRepository;
import com.example.pcbuilder.repository.GpuRepository;
import com.example.pcbuilder.repository.MotherboardRepository;
import com.example.pcbuilder.repository.ProcessorRepository;
import com.example.pcbuilder.repository.RamRepository;
import com.example.pcbuilder.repository.StorageDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/computers") // Указывает базовый URL для всех методов контроллера
public class PCController {

    @Autowired
    private PCRepository pcRepository; // Репозиторий для работы с компьютерами

    @Autowired
    private ProcessorRepository processorRepository; // Репозиторий для процессоров

    @Autowired
    private MotherboardRepository motherboardRepository; // Репозиторий для материнских плат

    @Autowired
    private GpuRepository gpuRepository; // Репозиторий для видеокарт

    @Autowired
    private RamRepository ramRepository; // Репозиторий для оперативной памяти

    @Autowired
    private StorageDeviceRepository storageDeviceRepository; // Репозиторий для накопителей

    // Получение всех компьютеров
    @GetMapping
    public String getAllPCs(Model model) {
        List<PC> pcs = pcRepository.findAll(); // Получаем список компьютеров
        model.addAttribute("pcs", pcs); // Добавляем в модель
        return "computers"; // Возвращаем имя шаблона
    }

    // Отображение формы для добавления нового компьютера
    @GetMapping("/add")
    public String addPC(Model model) {
        model.addAttribute("computer", new PC()); // Создаем новый объект компьютера
        model.addAttribute("processors", processorRepository.findAll()); // Загружаем список процессоров
        model.addAttribute("motherboards", motherboardRepository.findAll()); // Загружаем список материнских плат
        model.addAttribute("gpus", gpuRepository.findAll()); // Загружаем список видеокарт
        model.addAttribute("rams", ramRepository.findAll()); // Загружаем список оперативной памяти
        model.addAttribute("storageDevices", storageDeviceRepository.findAll()); // Загружаем список накопителей
        return "computer-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка добавления нового компьютера
    @PostMapping
    public String createPC(@ModelAttribute PC pc) {
        pcRepository.save(pc); // Сохраняем компьютер в репозитории
        return "redirect:/computers"; // Перенаправляем на список компьютеров
    }

    // Отображение формы для редактирования компьютера
    @GetMapping("/edit/{name}")
    public String editPC(@PathVariable String name, Model model) {
        PC pc = pcRepository.findById(name).orElse(null); // Ищем компьютер по имени
        if (pc == null) {
            model.addAttribute("error", "Компьютер не найден."); // Добавляем сообщение об ошибке
            return "redirect:/computers"; // Перенаправляем на список, если не найден
        }
        model.addAttribute("computer", pc); // Добавляем компьютер в модель
        model.addAttribute("processors", processorRepository.findAll()); // Загружаем список процессоров
        model.addAttribute("motherboards", motherboardRepository.findAll()); // Загружаем список материнских плат
        model.addAttribute("gpus", gpuRepository.findAll()); // Загружаем список видеокарт
        model.addAttribute("rams", ramRepository.findAll()); // Загружаем список оперативной памяти
        model.addAttribute("storageDevices", storageDeviceRepository.findAll()); // Загружаем список накопителей
        return "computer-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка обновления компьютера
    @PostMapping("/{name}")
    public String updatePC(@PathVariable String name, @ModelAttribute PC pc) {
        pc.setName(name); // Устанавливаем имя, чтобы идентифицировать объект
        pcRepository.save(pc); // Сохраняем изменения
        return "redirect:/computers"; // Перенаправляем на список компьютеров
    }

    // Обработка удаления компьютера
    @PostMapping("/delete/{name}")
    public String deletePC(@PathVariable String name) {
        pcRepository.deleteById(name); // Удаляем компьютер по имени
        return "redirect:/computers"; // Перенаправляем на список компьютеров
    }
}
