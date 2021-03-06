package edu.kea.adventureXP.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kea.adventureXP.model.Activity;
import edu.kea.adventureXP.model.ActivityController;
import edu.kea.adventureXP.view.ManageActivityUI;

/**
 * Presenter class for the ManageActivityUI. It gets and saves information about
 * activities for the view.
 * 
 * @see ManageActivityUI
 */
public class ManageActivityPresenter {
  
  private ManageActivityUI        ui;
  private ActivityViewerPresenter avp;
  private Activity                activity = null;
  private boolean                 edit     = false;
  
  /**
   * Constructor for wanting to open 'Manage Activity' with pre-filled fields.
   * 
   * @param ui UI for 'Manage Activity'
   * @param activity Object of Activity, which delivers information to the
   *          fields
   * @param avp The Viewer that should be updated after the activity has been
   *          edited
   */
  public ManageActivityPresenter(ManageActivityUI ui, Activity activity,
      ActivityViewerPresenter avp) {
    this(ui, avp);
    ui.setNameField(activity.getName());
    ui.setPriceField(activity.getPrice());
    ui.setDescriptionArea(activity.getDescription());
    ui.setIsActive(activity.getIsActive());
    this.activity = activity;
    edit = true;
  }
  
  /**
   * Constructor for 'Manage Activity', which adds listeners to the buttons.
   * 
   * @param ui UI for 'Manage Activity'
   * @param avp The Viewer that should be updated after a Activity has been
   *          saved
   */
  public ManageActivityPresenter(ManageActivityUI ui, ActivityViewerPresenter avp) {
    this.ui = ui;
    this.avp = avp;
    ui.setSaveListener(new SaveButtonListener());
    ui.setDiscardListener(new DiscardButtonListener());
  }
  
  /**
   * The class Constructor. It sets the button listeners for the buttons in
   * ManageActivityUI.
   *
   * @param ui The ManageActivityUI
   * @param controller The ActivityController
   */
  public ManageActivityPresenter(ManageActivityUI ui, ActivityController controller) {
    this.ui = ui;
    ui.setSaveListener(new SaveButtonListener());
    ui.setDiscardListener(new DiscardButtonListener());
  }
  
  /**
   * Default constructor
   */
  public ManageActivityPresenter() {
  }
  
  /**
   * Validates the name field in ManageActivityUI.
   * 
   * @param name The String in the text field.
   * @return true if the field is not empty
   */
  public boolean validateName(String name) {
    return !name.isEmpty();
  }
  
  /**
   * Validates the price field in ManageActivityUI.
   * 
   * @param price The price as double.
   * @return true if price is not negative.
   */
  public boolean validatePrice(double price) {
    return price >= 0;
  }
  
  /**
   * Inner private class implementing the behavior of an ActionListener. The
   * class has the behavior for saving an Activity.
   */
  private class SaveButtonListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
      String errorMessage = "";
      boolean flag = true;
      
      if (!validateName(ui.getNameField())) {
        errorMessage += "- Name field cannot be empty.\n";
        flag = false;
      }
      
      if (!validatePrice(ui.getPriceField())) {
        errorMessage += "- Price must be bigger than or equal to 0.\n";
        flag = false;
      }
      
      if (flag) {
        // Call the Controller
        if (edit) {
          activity.setDescription(ui.getDescriptionField());
          activity.setName(ui.getNameField());
          activity.setPrice(ui.getPriceField());
          activity.setIsActive(ui.getIsActive());
          ActivityController.updateActivity(activity);
        }
        else {
          Activity activity = new Activity(ui.getNameField(), ui.getDescriptionField(),
              ui.getPriceField(), ui.getIsActive());
          ActivityController.addActivity(activity);
        }
        avp.updateTable();
        ui.dispose();
      }
      else
        ui.displayError(errorMessage);
    }
    
  }
  
  /**
   * Inner private class implementing the behaviour of an ActionListener. The
   * class has the behaviour for discarding the changes made to an Activity.
   */
  private class DiscardButtonListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
      ui.dispose();
    }
    
  }
  
}
