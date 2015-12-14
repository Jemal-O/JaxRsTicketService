package objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransferDataCreation {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

//    получается, чтобы создать DataTransfer нужно сначала создать TransferDataCreation, в котром нет смысла.
//    Инстаницрование DataTransfer можно сделать через стаический initDataTransfer
    public DataTransfer initDataTransfer(String name, String lastName, String patronymicName, String departCity,
            String arrivalCity, String departDate, String departTime, String arrivalDate, String arrivalTime,
            String birthDate) throws ParseException {
        DataTransfer transfer = new DataTransfer();
        transfer.setArrivalCity(arrivalCity);
        transfer.setArrivalDate(dateFormat.parse(arrivalDate + " " + arrivalTime));
        transfer.setBirthDate(new SimpleDateFormat("dd-M-yyyy").parse(birthDate));
        transfer.setDepartCity(departCity);
        transfer.setDepartDate(dateFormat.parse(departDate + " " + departTime));
        transfer.setLastName(lastName);
        transfer.setName(name);
        transfer.setPatronymicName(patronymicName);
        return transfer;
    }
}