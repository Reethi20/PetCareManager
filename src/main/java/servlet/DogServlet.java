package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DogDAO;
import model.Dog;

@WebServlet("/dog")
public class DogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DogDAO dogDAO;

    @Override
    public void init() throws ServletException {
        dogDAO = new DogDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                showForm(request, response, null);
                break;
            case "edit":
                editDog(request, response);
                break;
            case "delete":
                deleteDog(request, response);
                break;
            case "list":
            default:
                listDogs(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            saveDog(request, response);
        } else {
            response.sendRedirect("dog?action=list");
        }
    }

    private void listDogs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Dog> dogList = dogDAO.getAllDogs();
        request.setAttribute("dogList", dogList);
        request.getRequestDispatcher("/WEB-INF/listDogs.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, Dog dog)
            throws ServletException, IOException {

        request.setAttribute("dog", dog);
        request.getRequestDispatcher("/WEB-INF/dogForm.jsp").forward(request, response);
    }

    private void editDog(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int dogId = 0;
        try {
            dogId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            dogId = 0;
        }

        Dog dog = dogDAO.getDogById(dogId);
        showForm(request, response, dog);
    }

    private void deleteDog(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idStr = request.getParameter("id");
        int dogId = 0;
        try {
            dogId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            dogId = 0;
        }

        dogDAO.deleteDog(dogId);
        response.sendRedirect("dog?action=list");
    }

    private void saveDog(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String dogIdStr = request.getParameter("dogId");
        String name = request.getParameter("name");
        String breed = request.getParameter("breed");
        String ageStr = request.getParameter("age");
        String gender = request.getParameter("gender");
        String weightStr = request.getParameter("weight");
        String color = request.getParameter("color");

        int dogId = 0;
        int age = 0;
        double weight = 0.0;

        try { dogId = (dogIdStr == null || dogIdStr.isEmpty()) ? 0 : Integer.parseInt(dogIdStr); }
        catch (NumberFormatException e) { dogId = 0; }

        try {
            if (ageStr != null && !ageStr.isEmpty()) {
                age = Integer.parseInt(ageStr);
            }
        } catch (NumberFormatException e) {
            age = 0;
        }

        try {
            if (weightStr != null && !weightStr.isEmpty()) {
                weight = Double.parseDouble(weightStr);
            }
        } catch (NumberFormatException e) {
            weight = 0.0;
        }

        int ownerId = 1; // static for now

        Dog dog = new Dog();
        dog.setDogId(dogId);
        dog.setOwnerId(ownerId);
        dog.setName(name);
        dog.setBreed(breed);
        dog.setAge(age);
        dog.setGender(gender);
        dog.setWeight(weight);
        dog.setColor(color);

        if (dogId == 0) {
            dogDAO.addDog(dog);
        } else {
            dogDAO.updateDog(dog);
        }

        response.sendRedirect("dog?action=list");
    }
}
