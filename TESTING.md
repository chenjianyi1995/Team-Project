Please read the raw file to get better vision

Who: Zijun Xu, Jianyi Chen, Jonathan Young, Zhiwen Shu, Xiaoyang Dou

Title: Damn The Ram

Vision: Keeping school spirit alive, one game at a time.

Automated Tests:


User Acceptance Tests:
1)
Use case name
	Verify user can control direction using arrow keys
Description
	Test movement control
Pre-conditions
	User has a functional keyboard
Test steps
	1.Run the game
	2.Press on different arrow keys
Expected result
	User should be able to move in all four directions by using arrow keys
Actual result
	Bufflalo can move in all four directions correctly
Status (Pass/Fail)
	Pass
Notes
	N/A
Post-conditions
	N/A

2)
Use case name
	Verify the player can not go over map boundary
Description
	Test map boundary	
Pre-conditions
	User has a founctional keyboard
	Verified that movement functions work accurately
Test steps
	1.Run the game
	2.Go to map boundary by using arrow keys
	3.See whether or not player can pass through the boundary
Expected result
	User shouldn't be able to move out of the map/pass through boundaries
Actual result
	User can not go over map boundary
Status (Pass/Fail)
	Pass
Notes
	N/A
Post-conditions
	N/A

3)
Use case name
	Verify the ram spawns at the spawner
Description
	To make sure the ram spawns at the spawner
Pre-conditions
	User has a founctional keyboard
	Verified that movement functions work accurately
Test steps
	1.Run the game
	2.Go to the spawner by using arrow key
	3.Observe if the ram is at the spawner
Expected result
	The ram should be seen at the spawner
Actual result
	The ram is there!
Status (Pass/Fail)
	Pass
Notes
	N/A
Post-conditions
	N/A
