import random

path = "G:/Mi unidad/Ayudante/Programación Avanzada 2021-I/Ayudantía 11/src/alma.txt"
arch = open(path,"w",encoding="utf-8")
roles = ["Ingeniero","Auxiliar","Astronomo"]
verificador=[0,1,2,3,4,5,6,7,8,9,"k"]
departamentos = ["Via Lactea,N100","Via Lactea,N98","Via Lactea,N97","Via Lactea,N96","Via Lactea,N95","Via Lactea,N94","Tierra,N54","Tierra,N82","Jupiter,N89","Jupiter,N69","Urano,N1","Andromeda,N98","Andromeda,N81","Andromeda,N89","Tierra,N78","Urano,N189","Via Lactea,N90","Via Lactea,N91","Via Lactea,N92","Via Lactea,N93"]
ruts=[]
for w in range(len(departamentos)):
    departamentos[w]="Calle "+departamentos[w]
personas_por_departamento=[0]*20
cantidadruts=250
print(len(departamentos))
print(cantidadruts)
sueldos=0
cant=0
for i in range(cantidadruts):
    rol=roles[random.randint(0,2)]
    rut=""
    while(True):
        rut=str(random.randint(4,17))
        veri=str(verificador[random.randint(0,10)])
        for x in range(2):
            rut+="."
            for y in range(3):
                rut+=str(random.randint(0,9))
        rut+="-"+veri
        if(rut not in ruts):
            break
        else:
            print(rut+" duplicado.")
    ruts.append(rut)
    text=rol+","+rut
    if rol=="Ingeniero":
        sueldo=str(random.randint(25,48)*100000) #2.500.000 - 4.800.000
        experiencia=str(random.randint(5,8))
        text+=","+sueldo+","+experiencia+","
        sueldos+=int(sueldo)
        cant+=1
    elif rol=="Auxiliar":
        sueldo=str(random.randint(65,71)*10000) #650.000 - 710.000
        text+=","+sueldo
    elif rol=="Astronomo":
        sueldo=str(random.randint(65,71)*100000) #6.500.000 - 7.100.000
        celestes=str(random.randint(6,90))
        text+=","+sueldo+","+celestes+","
    if rol!="Auxiliar":
        departamento=departamentos[random.randint(0,len(departamentos)-1)]
        while(personas_por_departamento[departamentos.index(departamento)]==100):
            departamento=departamentos[random.randint(0,len(departamentos)-1)]
        personas_por_departamento[departamentos.index(departamento)]+=1
        text+=departamento
    if i<(cantidadruts)-1:
        arch.write(text+"\n")
    else:
        arch.write(text)
arch.close
cont=0
print(sueldos/cant)
for o in range(len(departamentos)):
    if (personas_por_departamento[o]==100):
        cont+=1
        print(" ["+str(o+1)+"] En "+departamentos[o]+" hay "+str(personas_por_departamento[o])+" personas.")
    else:
        print(" ["+str(o+1)+"] En "+departamentos[o]+" hay \033[1m"+str(personas_por_departamento[o])+" personas\033[0m")
print("\nPorcentaje de optimización \033[1m"+str(cont/len(departamentos)*100)+"%\033[0m\n")