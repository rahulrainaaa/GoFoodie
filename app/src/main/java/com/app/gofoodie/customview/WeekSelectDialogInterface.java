package com.app.gofoodie.customview;


/**
 * @interface WeekSelectDialogInterface
 * @desc Interface for dialog buttoon ok callback.
 */
public interface WeekSelectDialogInterface {


    /**
     * @param dialog
     * @method weekDialogOkClicked
     * @desc Callback method to dialog button ok clicked.
     */
    public void weekDialogOkClicked(WeekSelectDialog dialog);

    /**
     * @param dialog
     * @method weekDialogCancelClicked
     * @desc Callback method to dialog button cancel clicked.
     */
    public void weekDialogCancelClicked(WeekSelectDialog dialog);



}
