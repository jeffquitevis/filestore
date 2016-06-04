package com.filestore.DataStore;

import com.filestore.Model.PersonModel;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeff on 5/11/2016.
 */
public class MemoryDataStore implements DataStore {


    public static Map<Integer, PersonModel> map = new HashMap<Integer, PersonModel>();

    @Override
    public void addPerson(PersonModel personModel) throws IOException {

        map.put(personModel.getId(), personModel);
    }


    @Override
    public PersonModel getPerson(int id) throws IOException{

        PersonModel personModel = null;

        if (map.containsKey(id)){

           personModel = map.get(id);
        }else{

            return null;
        }
        return personModel;
    }


    @Override
    public void delete(int id) {

        if (map.containsKey(id)){
           map.remove(id);
        }
    }

    @Override
    public List<PersonModel> getAllPerson() throws IOException, ClassNotFoundException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        List<PersonModel> personModelList = new ArrayList<PersonModel>();


        for (int x = 0; x < map.size(); x++){

            personModelList.add(map.get(x));

        }
        return personModelList;
    }

}
