package com.example.pcbuilder.controller;

import com.example.pcbuilder.model.StorageDevice;
import com.example.pcbuilder.repository.StorageDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/storagedevices") // Указывает базовый URL для всех методов контроллера
public class StorageDeviceController {

    @Autowired
    private StorageDeviceRepository storageDeviceRepository; // Репозиторий для работы с накопителями

    // Получение всех накопителей
    @GetMapping
    public String getAllStorageDevices(Model model) {
        List<StorageDevice> storageDevices = storageDeviceRepository.findAll(); // Получаем список накопителей
        model.addAttribute("storageDevices", storageDevices); // Добавляем в модель
        return "storagedevices"; // Возвращаем имя шаблона
    }

    // Отображение формы для добавления нового накопителя
    @GetMapping("/add")
    public String addStorageDevice(Model model) {
        model.addAttribute("storageDevice", new StorageDevice()); // Создаем новый объект накопителя
        return "storagedevice-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка добавления нового накопителя
    @PostMapping
    public String createStorageDevice(@ModelAttribute StorageDevice storageDevice) {
        storageDeviceRepository.save(storageDevice); // Сохраняем накопитель в репозитории
        return "redirect:/storagedevices"; // Перенаправляем на список
    }

    // Отображение формы для редактирования накопителя
    @GetMapping("/edit/{name}")
    public String editStorageDevice(@PathVariable String name, Model model) {
        StorageDevice storageDevice = storageDeviceRepository.findById(name).orElse(null); // Ищем накопитель по имени
        if (storageDevice == null) {
            return "redirect:/storagedevices"; // Перенаправляем, если запись не найдена
        }
        model.addAttribute("storageDevice", storageDevice); // Добавляем накопитель в модель
        return "storagedevice-form"; // Возвращаем имя шаблона для формы
    }

    // Обработка обновления накопителя
    @PostMapping("/edit/{name}") // Обработка обновления записи
    public String updateStorageDevice(@PathVariable String name, @ModelAttribute StorageDevice storageDevice) {
        storageDevice.setName(name); // Устанавливаем имя, чтобы сохранить его
        storageDeviceRepository.save(storageDevice); // Сохраняем изменения
        return "redirect:/storagedevices"; // Перенаправляем на список
    }

    // Обработка удаления накопителя
    @GetMapping("/delete/{name}")
    public String deleteStorageDevice(@PathVariable String name) {
        storageDeviceRepository.deleteById(name); // Удаляем накопитель по имени
        return "redirect:/storagedevices"; // Перенаправляем на список
    }
}
