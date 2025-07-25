# orion-expedition

 (Final project for course Object Oriented Programming at ELTE University BSc) (Java)


Task Description


Orion Expedition

In the vast reaches of space, a powerful space shuttle embarks on a voyage
to explore distant planets. To navigate this journey, the shuttle relies on
several key systems that work together to reach its destinations efficiently.
Destinations are planets or celestial bodies the shuttle aims to reach.
Each destination has a name, a distance form Earth, and a travel progress
to track the shuttle's travel progress towards it. The destination also has a
formula to calculate how far the shuttle is from the destination, so that it
can update its progress. Here we assume that all planets are in a line, so
the distance is just "distance of destination from Earth" - "how far we have
come from Earth".
The navigation system acts as the shuttle's guide, maintaining a list of
destinations. It allows adding new destinations and removing ones that have
been fully reached. The navigation determines which destination is nearest
to the shuttle, helping it plan the most efficient route.
The tank is responsible for fuel management: it keeps track of the current
fuel and the maximum fuel capacity. The tank reduces the available fuel
based on a percent usage, while refueling restores the tank to full capacity.
If the fuel drops below 15% of its maximum level, the system signals that it
is low on fuel. Of course, the tank can check how much fuel is left.
The main engine provides the thrust needed for space travel. It can start
and stop as required, but moving reduces fuel by 10% and increases the
shuttle's progress toward its next destination by 5%.
The orbiter is the shuttle's navigator. It has current destination that
starts with planet Earth, a next destination, and a reference to the shuttle,
To start the course, the orbiter takes the next destination, starts the engine,
and initiates movement while the engine is running. If fuel runs low, it
attempts to refuel at 50% chance before continuing. Otherwise, the engine
stops running. If a shuttle reaches its target by then, the orbiter updates its
current destination.
At the heart of it all is the space shuttle, which ties these components
together. It has a navigation system, an orbiter, an engine, and a fuel tank.
The shuttle can add destinations and launch into space, driving the orbiter
towards the closest destination. When a destination is successfully reached, it
is removed from the navigation. The launch is complete when all destinations
have been reached.
The main simulation serves as the entry point, initializing the space shut-
tle, setting destinations like Mercury, Jupiter, Saturn, Neptune, and Mars,
and launching the expedition. The shuttle systematically travels from one
destination to another, overcoming fuel challenges along the way. Once all
planets are visited, the mission is deemed a success. Simulate, launch the
shuttle, and show the actions!

Input file examples:
destinations.txt
. Format
Name Distance
. Sample input
Jupiter 1000
Saturn 500
Neptune 200
Mars 4000

(Second part - extension)


 Additional functionality

As the Orion space shuttle ventures deeper into the cosmos, NASA has de-
veloped advanced systems to enhance its exploration capabilities. Each des-
tination (celestial body) now includes a scientific value metric, observation
completion percentage, and specific fuel consumption rate.
The navigation system now features a navigation mode with three dis
tinct strategies to interstellar travel. Standard mode takes the direct ap-
proach found in the original design. Efficient mode factors fuel consumption
into routing decisions by calculating a fuel-to-distance ratio for each des
tination. Exploration mode chooses destinations with the highest value,
sometimes choosing longer paths deliberately to study cosmic phenomena of
interest.

 State diagram
The shuttle's operational behavior is now governed by a state model that
modifies its functionality. Initially, the shuttle is in the docked state, where
it can refuel but cannot move as the engine remains inactive. When the en-
gine starts, it transitions to cruising state, traveling at standard velocity by
consuming fuel per move and increasing travel progress by 5%. Upon reach-
ing a destination, the shuttle enters orbiting state, performing observations
with reduced fuel consumption at 3%. If fuel levels drop critically low, the
shuttle transitions to emergency state, where it stops the observation and
attempts to refuel. If refueling succeeds, the shuttle returns to the cruising
state; if unsuccessful, the engine stops, and shuttle returns to docked state.

 Scenarios to model with sequence diagrams:
   Scenario 1: Successful Planetary Visit
When the shuttle goes to a new destination, the navigation course begins
with the selected destination. The engine starts, and the shuttle enters
cruising state. As the shuttle advances, its movement gradually increases
the destination's travel progress until it reaches 100%. Once complete, the
current destination is updated, the destination is removed from the naviga-
tion system, and the closest destination becomes the next target using the
active navigation strategy.

When the fuel drops below 15% of capacity, the shuttle enters Emergency
state. The system attempts refueling with a 50% success chance. If success-
ful, the fuel is restored to maximum, and the mission continues. If unsuc-
cessful, the engine stops, halting progress until the situation is resolved. The
shuttle can implement an enhanced emergency protocol that enables solar
energy collection in deep space, modifying the random refueling chance based
on proximity to stars, or calculating a minimum-energy return trajectory to
the nearest known refueling point by selecting the closest destination with
refueling capabilities.

  Scenario 2: Fuel Emergency Management
When the fuel drops below 15% of capacity, the shuttle enters Emergency
state. The system attempts refueling with a 50% success chance. If success-
ful, the fuel is restored to maximum, and the mission continues. If unsuc-
cessful, the engine stops, halting progress until the situation is resolved. The
shuttle can implement an enhanced emergency protocol that enables solar
energy collection in deep space, modifying the random refueling chance based
on proximity to stars, or calculating a minimum-energy return trajectory to
the nearest known refueling point by selecting the closest destination with
refueling capabilities.
