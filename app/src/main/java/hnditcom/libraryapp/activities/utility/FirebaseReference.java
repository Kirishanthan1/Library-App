package hnditcom.libraryapp.activities.utility;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hnditcom.libraryapp.activities.model.User;

public class FirebaseReference {
    FirebaseDatabase firebaseDatabase;

    public FirebaseReference(){
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public DatabaseReference getUserDataReference(){
        return firebaseDatabase.getReference(Contants.USER_DATABASE_REFERENCE);
    }

    public void upDateUser(User user){
        getUserDataReference().child(user.id).setValue(user);

    }

    public DatabaseReference getBookDbReference(){
       return firebaseDatabase.getReference(Contants.Book_DATABASE_REFERENCE);
    }
}
