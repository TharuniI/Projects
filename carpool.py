#Beginning display#
print "@"*93
print " "* 38, "Carpool Inc."
print " " * 8, "With this software, users can make quick money registering as a driver,"
print " " * 8, "or can save money by applying as a client who seeks carpooling services."
print " " * 10, "It's simple, cheap, convenient, and highly efficient, so join us!"

#This is a set of values pre given
regno1= ["cid00"]
regno2 = ["cic00"]
regno= ""
num1= 5
num2= 10
x= 0
z= 0
t= 1
new_list= []
default= "   User Input:"

#This is a dictonary of pre registered drivers
dictionary_d= {
    "cid001" : ["Victoria", "Steeles"],
    "cid002" : ["Markham", "Ellesmere"],
    "cid003" : ["Neilson", "Ellesmere"],
    "cid004" : ["Morningside", "McNicoll"],
    "cid005" : ["Birchmount", "Sheppard"]

}

#This is a dictonary of pre registered carpoolers
dictionary_c= {
    "cic001" : ["Markham", "Ellesmere"],
    "cic002" : ["Birchmount", "Sheppard"],
    "cic003" : ["Victoria", "Steeles"],
    "cic004" : ["Markham", "Ellesmere"],
    "cic005" : ["Morningside", "McNicoll"],
    "cic006" : ["Victoria", "Steeles"],
    "cic007" : ["Morningside", "McNicoll"],
    "cic008" : ["Birchmount", "Sheppard"],
    "cic0010" : ["Neilson", "Ellesmere"]
}

#This is the set of information of the drivers that will be provided
#to the carpoolers
list_d = [["Tharuni Iranjan", "Grey Accura", "tharuni@hotmail.ca"], ["Sanorja Gunaseelan", "Black Honda", "sanorja@outlook.com"], ["Priya Shukla", "Blue Hyundai", "priya@hotmail.com"], ["Jake Peralta", "Black Toyota", "amy@hotmail.ca"], ["Rick Grimes", "Red Mazda", "twd@outlook.com"]]

#This creates a grid of the places covered by Carpool Inc
horizontal= ["Victoria", "Warden", "Birchmount", "Kennedy", "Midland", "Brimley", "McCowan", "Bellamy", "Markham", "Neilson", "Morningside"]
vertical= ["Steeles", "McNicoll", "Finch", "McLevin", "Sheppard", "Milner", "Progress", "Ellesmere", "Brimorton", "Lawrence", "Eglinton"]
grid= []

for i in range(len(horizontal)):
    for j in range(len(vertical)):
        grid.append([horizontal[i], vertical[j]])

#This runs continusouly
while True:
    print "\nRegistration " + str(t)
    comp_name = str(input( "1. Enter your companuy name: "))
    print default, comp_name

    #This asks the user if they wish to become a driver, carpooler or cancel a registration
    print "\n2. Do you wish to become driver, carpooler, or cancel a registration?"
    d_c_x= str(input( "   Enter d for driver, c for carpooler, or x for cancellation: "))
    d_c_x= d_c_x.lower()

    #This runs if they select driver
    if d_c_x == "d":
        #This prints what the user inputs in a neat manner
        print default, "Driver"

        #This asks the user for their name, prints it, and puts it in a li
        name= str(input("\n3. Enter your first and last name: "))
        name= name.title()
        new_list.extend(name)
        print default, name

        location = str(input("\n4. Enter the major intersection from where you live (no comma): "))
        location= location.title()
        location= location.split()
        print default, ", ".join(location)

        if location not in grid:
            print "\n   This location is not within our radius"
            continue

        car= str(input("\n5. Enter the colour and make of your car(no comma): "))
        car= car.title()
        car= car.split()
        new_list.extend(car)
        print default, ", ".join(car)

        email= str(input("\n6. Enter your email address:\n   "))
        new_list.extend(email)
        print default, email

        num1 += 1
        regno1.append(str(num1))
        regno= "".join(regno1)

        dictionary_d[regno]= location
        list_d.append(new_list)

    elif d_c_x == "c":
        print default, "Carpooler"
        name= str(input("\n3. Enter your first and last name: "))
        name= name.title()
        print default, name

        location = str(input("\n4. Enter the major intersection from where you live: "))
        location= location.title()
        location= location.split()
        print default, ", ".join(location)

        if location not in grid:
            print "\n   This location is not within our radius"
            continue

        address = str(input("\n5. Enter your address: "))
        address= address.title()
        print default, address

        for key in dictionary_d:
            if location == dictionary_d[key]:
                z += 1
                break
            else:
                x += 1

        if z < 0:
            carpool_d= "There are no drivers available in your area"
        else:
            carpool_d= list_d[x]
            carpool_d = ", ".join(carpool_d)

        num2 += 1
        regno2.append(str(num2))
        regno= "".join(regno2)

        dictionary_c[regno]= location

    elif d_c_x == "x":
        print default, "Cancellation"
        name= str(input("\n4. Enter your first and last name: "))
        name= name.title()
        print default, name
        d_or_c= str(input("\n3. Were you registered as a driver or carpooler: "))
        if d_or_c == "d":
            print default, "Driver"
            regno_x= str(input("\n5. Enter your registration number: "))
            print default, regno_x

            del dictionary_d[regno_x]

        elif d_or_c == "c":
            print default,"Carpooler"
            regno_x= str(input("\n5. Enter your registration number: "))
            print default, regno_x

            del dictionary_c[regno_x]

    class Registration:
        def __init__(self, name, regno, d_c_x):
            self.name = name
            self.regno= regno
            self.d_c_x= d_c_x

        def __repr__(self):
            if self.d_c_x == "d":
                self.d_c_x = "Driver"
                return "Name: " + self.name + ", Category: " + self.d_c_x + ", Registration No: " + str(self.regno) + "\n\nCongratulations on registering as a driver!\nEmails will be sent to you about new carpoolers registered to your car"
            elif self.d_c_x == "c":
                self.d_c_x = "Carpooler"
                return "Name: " + self.name + ", Category: " + self.d_c_x + ", Registration No: " + str(self.regno) + "\n\nThis is your designated driver: " + carpool_d + "\nYour information has been emailed to your driver. Services will begin the following week."
            elif self.d_c_x == "x" and d_or_c == "d":
                return "You have successfully cancelled your registration with Carpool Inc.\nIMPORTANT:you must continue services for a week until each of your\ncarpoolers get a new driver."
            elif self.d_c_x and d_or_c == "c":
                return "You have successfully cancelled your registration with Carpool Inc.\nThis is your designated driver:\nYour information has been emailed to your driver. Services will begin the following week.\nYour designated driver will be informed of your cancellation."


    r= Registration
    print ""
    print r(name, regno, d_c_x)

    t += 1
    print ""
    exit= str(input("Enter e if you wish to exit or c if you wish to complete another registration: "))
    if exit == "e":
        break

print ""
print "@"*93
