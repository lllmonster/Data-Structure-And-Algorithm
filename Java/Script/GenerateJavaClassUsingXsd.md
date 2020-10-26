# Generate Java Class using Xsd file
[Ref](https://memorynotfound.com/generate-java-classes-from-xsd/)

## Read xml file to java object
```Java
File xmlFile = new File(xmlPath);
  
JAXBContext jaxbContext;
try {
  jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
  JAXBElement<?> ele = (JAXBElement<?>) jaxbContext.createUnmarshaller().unmarshal(xmlFile);
  MyCustomModel myCustomModel = (MyCustomModel) ele.getValue();
  log.info("myCustomModelId: " + myCustomModelId.getModelId());
} catch (JAXBException e) {
  e.printStackTrace();
}
```
