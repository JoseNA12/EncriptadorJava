package controlador.escritores;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import controlador.IEscritura;
import datosDTO.AlgoritmosDTO;
import modelo.estructuras.Resultados;

public class EscritorXML implements IEscritura {

    String ruta = "src/bitacoras/filename.xml";

    @Override
    public Boolean Escribir(AlgoritmosDTO miDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Resultados.class);
            // Leer XML
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            //Resultados resultados = (Resultados) unmarshaller.unmarshal(DIRECTORIO);
            //Servers servers = (Servers) unmarshaller.unmarshal(configFile);

            //Server server = new Server("abv", "1.9.3.5", 8080);

            //List<Server> serversList = servers.getServers();
            //serversList.add(server);

            //servers.setServers(serversList);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //marshaller.marshal(servers, configFile);
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
        /*boolean escribio = false;
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("company");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("Staff");
            rootElement.appendChild(staff);

            // set attribute to staff element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            // firstname elements
            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode("yong"));
            staff.appendChild(firstname);

            // lastname elements
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("mook kim"));
            staff.appendChild(lastname);

            // nickname elements
            Element nickname = doc.createElement("nickname");
            nickname.appendChild(doc.createTextNode("mkyong"));
            staff.appendChild(nickname);

            // salary elements
            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode("100000"));
            staff.appendChild(salary);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ruta));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            escribio = true;

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
        return escribio;*/
        return true;
    }
}
