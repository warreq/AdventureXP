package edu.kea.adventureXP.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kea.adventureXP.model.Instructor;
import edu.kea.adventureXP.model.InstructorController;
import edu.kea.adventureXP.view.ManageInstructorUI;

public class ManageInstructorPresenter {
  
  ManageInstructorUI        iui;
  Instructor                instructorToEdit;
  InstructorViewerPresenter ivp;
  private boolean           isEdit;
  
  public ManageInstructorPresenter() {
    isEdit = false;
    iui = new ManageInstructorUI();
  }
  
  public ManageInstructorPresenter(ManageInstructorUI iui, InstructorViewerPresenter ivp) {
    isEdit = false;
    this.iui = iui;
    this.ivp = ivp;
    iui.setSaveListener(new SaveButtonListener());
    iui.setDiscardListener(new DiscardButtonListener());
  }
  
  public ManageInstructorPresenter(ManageInstructorUI iui, InstructorViewerPresenter ivp,
      Instructor instructorToEdit) {
    this.iui = iui;
    this.ivp = ivp;
    iui.setFields(instructorToEdit);
    this.instructorToEdit = instructorToEdit;
    isEdit = true;
  }
  
  public boolean validateFirstName(String name) {
    return !name.isEmpty();
  }
  
  public boolean validateLastName(String lname) {
    return !lname.isEmpty();
  }
  
  public boolean validateStreetField(String street) {
    return !street.isEmpty();
  }
  
  public boolean validateStreetNumField(String string) {
    return Integer.parseInt(string) > 0;
  }
  
  public boolean validateCityField(String cityName) {
    return !cityName.isEmpty();
  }
  
  public boolean validateZipField(String zipCode) {
    return !zipCode.isEmpty();
  }
  
  public boolean validatePhoneField(String string) {
    return string.length() >= 7;
  }
  
  public boolean validateEmailField(String email) {
    if (email.contains("@") && email.contains(".") && !email.isEmpty())
      return true;
    else
      return false;
  }
  
  /**
   * 
   * SaveButtonListener is a private class implementing the behavior of an
   * ActionListener. The class has the behavior for saving informations about a
   * person..
   *
   */
  
  private class SaveButtonListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
      String errorMessage = "";
      boolean flag = true;
      
      if (!validateFirstName(iui.getFNameField())) {
        errorMessage += "- First name field cannot be empty.\n";
        flag = false;
      }
      
      if (!validateLastName(iui.getLNameField())) {
        errorMessage += "- Last name field cannot be empty.\n";
        flag = false;
      }
      
      if (!validateStreetField(iui.getStreetField())) {
        errorMessage += "Street cannot be empty.\n";
        flag = false;
      }
      
      if (!validateCityField(iui.getCityField())) {
        errorMessage += "City field cannot be empty. \n";
        flag = false;
      }
      
      if (!validateZipField(iui.getZipField())) {
        errorMessage += "Zip field cannot be empty. \n";
        flag = false;
      }
      
      if (!validatePhoneField(iui.getPhoneField())) {
        errorMessage += "Phone number should contain 7 or more digits. \n";
        flag = false;
      }
      
      if (!validateEmailField(iui.getEmailField())) {
        errorMessage += "Email should contain . and @ . And it should not be empty. \n";
        flag = false;
        
      }
      if (flag) {
        if (isEdit) {
          iui.setFNameField(instructorToEdit.getFirstName());
          iui.setLNameField(instructorToEdit.getLastName());
          iui.setStreetField(instructorToEdit.getStreet());
          iui.setCityField(instructorToEdit.getCity());
          iui.setZipField(instructorToEdit.getZipCode());
          iui.setPhoneField(instructorToEdit.getTelephone());
          iui.setEmailField(instructorToEdit.getEmail());
          InstructorController.updateInstructor(instructorToEdit);
        }
        else {
          Instructor instructor = new Instructor(iui.getFNameField(),
              iui.getLNameField(), iui.getStreetField(), iui.getZipField(),
              iui.getCityField(), "Denmark", iui.getPhoneField(), iui.getEmailField());
          
          InstructorController.addInstructor(instructor);
        }
        ivp.updateTable();
        iui.dispose();
      }
      else
        iui.displayError(errorMessage);
    }
  }
  
  /**
   * DiscardButtonListener is a private class implementing the behavior of an
   * ActionListener. The class has the behavior for discarding the changes made
   * on the information about a person.
   */
  private class DiscardButtonListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
      iui.dispose();
    }
    
  }
}
