# DateTimePickerDialog

In Android, there are separate pickers to select date and time namely the DatePickerDialog and the TimePickerDialog. If we need them as a view, so that we can decide where to show and how to show them, android provides DatePicker and TimePicker. But there are a lot of use cases where we need both of them (eg: To select date and time for a meeting, for a cricket match, etc). Right now android provides no such picker where we can select both date and time. For now, users must put their own dialog/popup window and attach the date and time picker views. Yes this involves some time and code which I have experienced while working on this type of field in UISDK. So, why not create a DateTimePickerDialog ? 

Features:
1. Set 3 types of buttons, namely Positive button (eg: OK), Negative button (eg: CANCEL) and Neutral button (eg: CLEAR) and listeners for them. 
2. Date and Time change listeners. 
3. SetDateTime which loads preset date and time. 
4. Options to customise picker themes and tab colours, tab text colours, etc. 

In this widget, view pager 2 is used that enables user to swipe between the tabs. Tab layout is used which holds the 2 fragments DATE and TIME. These fragments reside inside a dialog which itself is a fragment, the Dialog fragment. 

How to use this: 
val dateTimePickerDialog = DateTimePickerDialog() 
dateTimePickerDialog( fragmentManager, TAG ) 
//dateTimePickerDialog. -> to access properties like themes, listeners, etc. 
