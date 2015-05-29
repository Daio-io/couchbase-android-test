package io.daio.couchy.Database;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;
import java.util.Map;

/**
 * Main Database Class to wrap up CRUD operations
 */
public class CouchyDatabase {

    // Android Context
    private Context context;
    // CouchBase DB
    private Manager manager;
    private Database database;

    public CouchyDatabase(String _dbName, Context _context) {

        context = _context;
        initialiseDatabase(_dbName);

    }

    /**
     * @param document
     * @return {String} The saved document ID
     * @method createDocument
     * @description Method to store a new document in the database
     */
    public String createDocument(Map<String, Object> document) {

        Document newDocument = database.createDocument();

        try {

            newDocument.putProperties(document);

        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        return newDocument.getId();
    }

    /**
     * @param {String} documentId
     * @return {Map<String, Object>} The document object
     * @method getDocumentById
     * @description Get a saved document by its stored ID
     */
    public Map<String, Object> getDocumentById(String documentId) {

        Document retrievedDocument = database.getDocument(documentId);

        return retrievedDocument.getProperties();

    }

    /**
     * @param {String} documentId
     * @return {Boolean} Confirms if the document was deleted
     * @method deleteDocumentById
     * @description Deletes a saved document by its stored ID
     */
    public Boolean deleteDocumentById(String documentId) {

        Document documentToDelete = database.getDocument(documentId);
        try {
            documentToDelete.delete();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        return documentToDelete.isDeleted();

    }


    /**
     * @param dbName - Name of database to find or create
     * @private
     * @method initialiseDatabase
     * <p/>
     * @description Initialises the connection to the database creating a new
     * database if it does not exist.
     */
    private void initialiseDatabase(String dbName) {

        try {
            manager = new Manager(new AndroidContext(this.context),
                    Manager.DEFAULT_OPTIONS);

            database = manager.getDatabase(dbName);

        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
