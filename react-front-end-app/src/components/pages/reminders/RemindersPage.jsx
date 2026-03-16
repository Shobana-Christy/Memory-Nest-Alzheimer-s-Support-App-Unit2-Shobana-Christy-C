import { Link } from "react-router";
import Tile from "../../common/Tile";
import { useEffect, useState } from 'react'
import LoadingPage from "../LoadingPage";
import Reminder from "./reminder";
import { FaTrash } from "react-icons/fa";
import ReminderForm from "./ReminderForm";
import { fetchReminders, addReminder, updateReminder, deleteReminder } from './../../common/dataCollection';
import Alert from '../../common/Alert'

const RemindersPage = () => {
    let [isLoading, setIsLoading] = useState(true);
    let [alert,setAlert] = useState(null);
    let [reminders, setReminders] = useState(null);
    // show the popup form
    let [showAddEditReminder, setShowAddEditReminder] = useState(false);
    const initialReminderData = {
        id: null,
        name: "",
        date: "",
        time: "",
        notes: ""
    };
    useEffect(() => {
        if(alert) {
            // after 1 second, remove the success message alert
            const timer = setTimeout(() => setAlert(null), 1000);
            return () => clearTimeout(timer); // clears the timeout operation
        }
    }, [alert]);

    // to track the edit operation
    let [reminder, setReminder] = useState(initialReminderData);
    useEffect(() => {
        const responsePromiseOne = fetchReminders();
        responsePromiseOne.then((response) => {
            setIsLoading(false);
            if(response.success == true) {
                setReminders(response.data);
            } else {
                setReminders([]);
                setAlert(response.error);
            }
        });
    }, []);

    const handleSave = (newOrUpdatedReminder) => {
        const {name, date, time, notes} = {...newOrUpdatedReminder};
        if (reminder.id) {
            let localReminderList = [...reminders];
            let reminderToUpdate = localReminderList.find(r => r.id == reminder.id);
            reminderToUpdate.name = name;
            reminderToUpdate.date = date;
            reminderToUpdate.time = time;
            reminderToUpdate.notes = notes;
            const responsePromise = updateReminder(reminderToUpdate);
            responsePromise.then((response) => {
                if (response.success == true) {
                    let updatedReminder = response.data;
                    let existingReminders = reminders.filter(r => r.id != updatedReminder.id);
                    if(response.success == true) {
                        setReminders([...existingReminders, updatedReminder]);
                        setAlert("Successfully updated the reminder.");
                    } else {
                        setAlert(response.error);
                    }
                }
            });
        } else {
            const newReminder = new Reminder(null, name, date, time, notes);
            const responsePromise = addReminder(newReminder);
            responsePromise.then((response) => {
                 if(response.success == true) {
                     let savedReminder = response.data;
                    setReminders([...reminders, savedReminder]);
                    setAlert("Successfully added the reminder.");
                } else {
                    setAlert("Error adding the reminder, "+response.error);
                }
            });
        }
        setShowAddEditReminder(false);
    };

    const showEdit = (reminderId) => {
        const reminder = reminders.find(reminder => reminder.id == reminderId);
        setShowAddEditReminder(true);
        setReminder(reminder);
    }

    const toggleReminder = () => {
        setShowAddEditReminder(!showAddEditReminder);
        if (!showAddEditReminder) {
            setReminder(initialReminderData);
        }
    }

    const handleDelete = (event, reminderId) => {
        
        //- Stops the default behavior of the element.
        event.preventDefault();      
        event.stopPropagation();    //Stops the event from bubbling up to parent elements.
        // //- Without this, clicking delete might also trigger the parent <Link> click, which opens the edit form.
        //This ensures only the delete action runs.
        const responsePromise = deleteReminder(reminderId);
        responsePromise.then((response) => {
            if (response.success == true) {
                const remindersAfterDelete = [...reminders].filter(r => r.id != reminderId);
                setReminders(remindersAfterDelete);
                setAlert("Successfully deleted the reminder");
            } else {
                setAlert("Error deleting the reminder, "+response.error);
            }
        });
    }

    return (
        <main>
            <div className="main-page-header">

                <div className="sub-nav">
                    <Link to="/home" className="link">Home</Link>
                </div>

                <h3>Reminders</h3>
            </div>
            <div className="add-link">
                <a className="link" onClick={toggleReminder} ><span>+</span>Add reminder</a>
            </div>

            <div id="main-content-reminders">
                {
                    isLoading &&
                    (<LoadingPage dataName="reminders" />)
                }
                {
                    !isLoading && reminders && reminders.length == 0 &&
                    (<p><em>No reminders are set</em></p>)
                }
                {
                    !isLoading && reminders && reminders.length > 0 && (

                        reminders.map((reminder) => (
                            <Link className="link" key={reminder.id} onClick={() => showEdit(reminder.id)}>
                                <Tile>
                                    <div className="reminder-content">
                                        <h3>{reminder.name}</h3>
                                        <p>{reminder.time ? `${reminder.date} ${reminder.time}` : reminder.date}</p>
                                        <p>{reminder.notes}</p>
                                    </div>
                                    <div className="reminder-action">
                                        <button name="delete"
                                            className="reminder-delete-icon"
                                            onClick={(event) => handleDelete(event, reminder.id)}>
                                            <FaTrash />
                                        </button>
                                    </div>
                                </Tile>
                            </Link>
                        ))
                    )
                }

            </div>

            {
                (showAddEditReminder) &&
                <ReminderForm reminder={reminder} onClose={toggleReminder} onSave={handleSave}/>
            }
            {
                alert &&
                <Alert message = {alert} />
            }
        </main>
    );
};

export default RemindersPage;
