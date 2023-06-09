import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;

public class MCFogger extends Applet implements Runnable, KeyListener, ActionListener, MouseListener
{
    // Title Screen
    String title = "Minecraft Frogger";

    Thread main = new Thread(this);

    Image buffer;
    Graphics bufferG;

    Level Levels[] = new Level[3];

    // Levels
    Level levelOne = new Level("Forest Biome");
    Level levelTwo = new Level("Cave");

    // Assets & Images
    Image playerIMG_one;
    Image playerIMG_two;
    Image dirtBlockIMG;
    Image dirtBlockTopIMG;
    Image dirtBlockTopFlowerIMG;
    Image stoneBlockTopIMG;
    Image sandBlockTopIMG;
    Image waterBlockTopIMG;
    Image caveOverlayIMG;
    Image caveLevelIMG;
    Image forestLevelIMG;
    Image treeIMG;
    Image houseIMG;
    Image playerHUD;
    Image hudWoodItem;
    Image hudSwordItem;
    Image hudHeartIcon;
    Image creeperTexture;

    // Buttons
    Button startgameBTN = new Button("Start Game");
    Button playLevelsBTN = new Button("Play Levels");

    // Level Buttons
    Button forestLevelBTN = new Button("Play Forest Level");
    Button caveLevelBTN = new Button("Play Cave Level");

    // Control Buttons
    Button upArrowBTN = new Button("UP");
    Button leftArrowBTN = new Button("LEFT");
    Button rightArrowBTN = new Button("RIGHT");

    // Forest Level Metadata

    Button ControlButtons[] = new Button[3];

    int scrollPosition = 0; // Increments +1 every time player goes up.

    int currentLevel = 0;

    int playerX = 600;
    int playerY = 440;

    int animDelay = 250;

    int playerHealth = 5;

    boolean showTitleScreen = true;

    boolean showLevelsPage = false;

    int currentPlayerStep = 0;

    int blockSize = 117;  // (700 / 3) / 2

    int jumpDistance = 175;

    boolean developerMode = false; // Used to skip the title screen, and to display useful info like playerX, and playerY.

    boolean showTitleStartText = true;

    int levelOneLevelDataSize = 25;

    String levelOneLevelDataList[] = new String[levelOneLevelDataSize];

    int levelTwoLevelDataSize = 15;

    String levelTwoLevelDataList[] = new String[levelTwoLevelDataSize];

    boolean swordEquiped = false;

    boolean holdingSword = false;

    boolean choppedTrees = false;

    boolean showEndCredits = false;

    boolean showDeathScreen = false;

    MovingPic creepers[] = new MovingPic[3];

    int speed = -45;

    Random rand = new Random();

    Rectangle creeper1;
    Rectangle creeper2;
    Rectangle creeper3;

    int titleScreenIndex = 0;

    int playerScore = 0;

    // alert data
    boolean showAlert = false;
    String alertTitle;
    String alertText;

    int selectedLevelIndex = 0;

    int endScreenIndex = 0;

    int mobsKilled = 0;

    int swordRadiusMultiplierX = 175;
    int swordRadiusMultiplierY = 125;

    boolean swordHitAvailable = false;

    public void resetPlayerData()
    {
        scrollPosition = 0;

        playerX = 600;
        playerY = 440;

        playerHealth = 5;

        currentPlayerStep = 0;;

        swordEquiped = false;

        holdingSword = false;

        choppedTrees = false;

        playerScore = 0;

        showAlert = false;
    }

    public void startDefaultGame()
    {
        showDeathScreen = false;
        showTitleScreen = false;
        showLevelsPage = false;
        showEndCredits = false;

        currentLevel = 0;

        resetPlayerData();

        for(int b = 0; b <= 2; b++)
        {
            creepers[b].setAlive(true);
            creepers[b].start();
        }
    }

    public void startCaveLevel()
    {
        showDeathScreen = false;
        showTitleScreen = false;
        showLevelsPage = false;
        showEndCredits = false;

        currentLevel = 1;
    }

    public void drawPlayer(Graphics g)
    {
        Image playerImg;

        if (currentPlayerStep == 0)
            playerImg = playerIMG_one;
        else
            playerImg = playerIMG_two;

        //g.setColor(Color.red);
        //g.fillRect(playerX - swordRadiusMultiplierX, playerY - swordRadiusMultiplierY, 165 + (swordRadiusMultiplierX * 2), 165 + (swordRadiusMultiplierY * 2));

        g.drawImage(playerImg, playerX, playerY, 165, 165, this);
    }

    public void changeStep()
    {
        if (currentPlayerStep == 0)
            currentPlayerStep = 1;
        else
            currentPlayerStep = 0;

        showTitleStartText = false;
    }

    public void showLevelsPage()
    {
        showLevelsPage = true;
    }

    public void drawTree(Graphics g, int x, int y)
    {
        g.drawImage(treeIMG, 200, 200, 350, 350, this);
    }

    public void drawPickup(String type)
    {
    }

    public void drawGrassLevel(String levelType, Graphics g, String position, int xPosition, String variant) // water varaints, lava pool. Positions, top, middle, bottom
    {
        for(int x = 0; x <= 12; x++)
        {
            g.drawImage(dirtBlockTopIMG, (blockSize * x), xPosition, blockSize, blockSize, this);

            g.drawImage(dirtBlockTopIMG, (blockSize * x), xPosition + blockSize, blockSize, blockSize, this);
        }

        if (currentLevel == 0) {
            if (scrollPosition == 0 && !choppedTrees)
                g.drawImage(treeIMG, 120, 200, 450, 450, this);
            else if (scrollPosition == 1 && !choppedTrees)
                g.drawImage(treeIMG, 120, 570, 450, 450, this);
            else if (scrollPosition == 3)
                g.drawImage(houseIMG, 1000, -20, 512, 512, this);
            else if (scrollPosition == 2)
                g.drawImage(houseIMG, 1000, -250, 512, 512, this);
            else if (scrollPosition == 4)
                g.drawImage(houseIMG, 1000, 170, 512, 512, this);
            else if (scrollPosition == 5)
                g.drawImage(houseIMG, 1000, 550, 512, 512, this);
            else if (scrollPosition == 5)
                g.drawImage(houseIMG, 1000, 550, 512, 512, this);
            if (scrollPosition == 18 && !choppedTrees)
                g.drawImage(treeIMG, 820, -120, 450, 450, this);
            if (scrollPosition == 19 && !choppedTrees)
                g.drawImage(treeIMG, 820, 200, 450, 450, this);
            else if (scrollPosition == 20 && !choppedTrees)
                g.drawImage(treeIMG, 820, 570, 450, 450, this);
            if (scrollPosition == 19 && !choppedTrees)
                g.drawImage(treeIMG, 80, -120, 450, 450, this);
            if (scrollPosition == 20 && !choppedTrees)
                g.drawImage(treeIMG, 80, 200, 450, 450, this);
            else if (scrollPosition == 21 && !choppedTrees)
                g.drawImage(treeIMG, 80, 570, 450, 450, this);
        }
    }

    public void drawStoneLevel(String levelType, Graphics g, String position, int xPosition, String variant) // water varaints, lava pool. Positions, top, middle, bottom
    {
        for(int x = 0; x <= 12; x++)
        {
            g.drawImage(stoneBlockTopIMG, (blockSize * x), xPosition, blockSize, blockSize, this);
            g.drawImage(stoneBlockTopIMG, (blockSize * x), xPosition + blockSize, blockSize, blockSize, this);
        }
    }

    public void drawSandLevel(String levelType, Graphics g, String position, int xPosition, String variant, Boolean top) // water varaints, lava pool. Positions, top, middle, bottom
    {
        for(int x = 0; x <= 12; x++)
        {
            g.drawImage(top ? sandBlockTopIMG : dirtBlockTopIMG, (blockSize * x), xPosition, blockSize, blockSize, this);
            g.drawImage(top ? dirtBlockTopIMG : sandBlockTopIMG, (blockSize * x), xPosition + blockSize, blockSize, blockSize, this);
        }
    }

    public void drawWaterLevel(String levelType, Graphics g, String position, int xPosition, String variant) // water varaints, lava pool. Positions, top, middle, bottom
    {
        for(int x = 0; x <= 12; x++)
        {
            g.drawImage(waterBlockTopIMG, (blockSize * x), xPosition, blockSize, blockSize, this);
            g.drawImage(waterBlockTopIMG, (blockSize * x), xPosition + blockSize, blockSize, blockSize, this);
        }
    }

    public void drawLevel(Graphics g, Level level)
    {
        for(int b = 0; b <= 2; b++)
        {
            int index = scrollPosition + b;

            if (index < levelOneLevelDataSize) {
                String levelType = currentLevel == 0 ? levelOneLevelDataList[index] : levelTwoLevelDataList[index]; 

                String position;

                int xPosition;

                if (b == 2)
                {
                    position = "bottom";

                    xPosition = 0;
                }
                else if (b == 1)
                {
                    position = "middle";

                    xPosition = 234;
                }
                else
                {
                    position = "top";

                    xPosition = 468;
                }

                if (levelType == "grass")
                {
                    drawGrassLevel(levelType, g, position, xPosition, "none");
                } else if (levelType == "stone") {
                    drawStoneLevel(levelType, g, position, xPosition, "tree");
                } else if (levelType == "water") {
                    drawWaterLevel(levelType, g, position, xPosition, "tree");
                } else if (levelType == "sandTop") {
                    drawSandLevel(levelType, g, position, xPosition, "tree", true);
                } else if (levelType == "sandBottom") {
                    drawSandLevel(levelType, g, position, xPosition, "tree", false);
                }

                if (developerMode)
                {
                    g.setColor(Color.white);

                    g.setFont(new Font("Ariel", 1, 20));

                    g.drawString("Index: " + (index + 1), 45, xPosition + 45);
                }
            }
        }
    }

    public void actionPerformed( ActionEvent e )
    {
        Boolean checkMenuBtns = showTitleScreen || showLevelsPage;

        if (checkMenuBtns)
        {
            showTitleScreen = false;
        } else {
            if (e.getSource() == startgameBTN)
            {
                startgameBTN.setVisible(false);

                playLevelsBTN.setVisible(false);

                showTitleScreen = false;

                showLevelsPage = false;

                for(int b = 0; b <= 2; b++)
                {
                    Button controlButton = ControlButtons[b];

                    controlButton.setVisible(true);
                }

                main.start();
            } else if (e.getSource() == playLevelsBTN)
            {
                startgameBTN.setVisible(false);

                playLevelsBTN.setVisible(false);

                forestLevelBTN.setVisible(true);

                caveLevelBTN.setVisible(true);

                showLevelsPage = true;

                showTitleScreen = false;
            } else if (e.getSource() == forestLevelBTN || e.getSource() == caveLevelBTN)
            {
                startgameBTN.setVisible(false);

                playLevelsBTN.setVisible(false);

                forestLevelBTN.setVisible(false);

                caveLevelBTN.setVisible(false);

                showTitleScreen = false;

                showLevelsPage = false;

                if (e.getSource() == caveLevelBTN)
                    currentLevel = 1;
                else
                    currentLevel = 0;

                for(int b = 0; b <= 2; b++)
                {
                    Button controlButton = ControlButtons[b];

                    controlButton.setVisible(true);
                }
            } else if (e.getSource() == leftArrowBTN)
            {
                playerX = playerX - 100;
            } else if (e.getSource() == rightArrowBTN)
            {
                playerX = playerX + 100;
            }
        }

        repaint();
    }

    public void init()
    {
        this.setLayout(null);

        Levels[0] = levelOne;
        Levels[1] = levelTwo;

        for(int x = 0; x <= 13; x++)
            levelOneLevelDataList[x] = "grass";

        levelOneLevelDataList[14] = "sandTop";

        levelOneLevelDataList[15] = "water";
        levelOneLevelDataList[16] = "water";

        levelOneLevelDataList[17] = "sandBottom";

        levelOneLevelDataList[18] = "grass";
        levelOneLevelDataList[19] = "grass";
        levelOneLevelDataList[20] = "grass";
        levelOneLevelDataList[21] = "grass";
        levelOneLevelDataList[22] = "grass";
        levelOneLevelDataList[23] = "grass";
        levelOneLevelDataList[24] = "grass";

        for(int x = 0; x <= 14; x++)
            levelTwoLevelDataList[x] = "stone";

        resize(1400, 700);
        buffer = createImage(this.getWidth(), this.getHeight());
        bufferG = buffer.getGraphics();
        playerIMG_one = this.getImage(this.getCodeBase(), "assets/steve_2.png");
        playerIMG_two = this.getImage(this.getCodeBase(), "assets/steve.png");
        dirtBlockIMG = this.getImage(this.getCodeBase(), "assets/textures/dirt_block.jpg");
        dirtBlockTopIMG = this.getImage(this.getCodeBase(), "assets/textures/dirt_top.jpg");
        stoneBlockTopIMG = this.getImage(this.getCodeBase(), "assets/textures/stone_top.png"); 
        sandBlockTopIMG = this.getImage(this.getCodeBase(), "assets/textures/sand_top.png"); 
        waterBlockTopIMG = this.getImage(this.getCodeBase(), "assets/textures/water_top.png"); 
        dirtBlockTopFlowerIMG = this.getImage(this.getCodeBase(), "assets/textures/dirt_top_flower.png");
        caveOverlayIMG = this.getImage(this.getCodeBase(), "assets/textures/cave_overlay.png");
        caveLevelIMG = this.getImage(this.getCodeBase(), "assets/levels/cave.png");
        forestLevelIMG = this.getImage(this.getCodeBase(), "assets/levels/forest.png");
        treeIMG = this.getImage(this.getCodeBase(), "assets/textures/tree.png");
        houseIMG = this.getImage(this.getCodeBase(), "assets/textures/house.png");
        playerHUD = this.getImage(this.getCodeBase(), "assets/hud.png");
        hudWoodItem = this.getImage(this.getCodeBase(), "assets/hud_wood.png");
        hudSwordItem = this.getImage(this.getCodeBase(), "assets/hud_sword.png");
        hudHeartIcon = this.getImage(this.getCodeBase(), "assets/hud_heart.png");
        creeperTexture = this.getImage(this.getCodeBase(), "assets/textures/creeper.png");
        for(int b = 0; b <= 2; b++)
        {
            creepers[b] = new MovingPic(
                creeperTexture,
                20,
                20,
                20,
                speed,
                0
            );
        }

        addKeyListener(this);

        creeper1 = new Rectangle(creepers[0].getX(), creepers[0].getY(), 165, 165);
        creeper2 = new Rectangle(creepers[1].getX(), creepers[1].getY(), 165, 165);
        creeper3 = new Rectangle(creepers[2].getX(), creepers[2].getY(), 165, 165);

        main.start();
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color.white);

        bufferG.fillRect(0, 0, this.getWidth(), this.getHeight());

        bufferG.setColor(Color.blue);

        if (showEndCredits || showTitleScreen || showLevelsPage || showDeathScreen)
        // Draw BG
            for(int x = 0; x <= 10; x++)
                for(int i = 0; i <= 5; i++)
                    bufferG.drawImage(dirtBlockIMG, 125 * x, 125 * i, 125, 125, this);

        if ((showTitleScreen || showLevelsPage) /*&& !developerMode*/)
        {
            if (showLevelsPage)
            {
                bufferG.setColor(Color.black);

                bufferG.fillRect(225, 65, 950, 460);

                bufferG.fillRect(225, 545, 950, 90);

                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 30));

                bufferG.drawString("Levels", 650, 120);  

                bufferG.drawImage(forestLevelIMG, 275, 165, 350, 200, this);

                bufferG.drawImage(caveLevelIMG, 675, 165, 350, 200, this);

                bufferG.drawString("Cave Level", 675, 420);  

                bufferG.drawString("Forest Level", 275, 420);  

                if (selectedLevelIndex == 1)
                {
                    bufferG.setColor(Color.darkGray);
                } else
                {
                    bufferG.setColor(Color.gray);
                }

                bufferG.fillRect(675, 450, 350, 45);

                if (selectedLevelIndex == 0)
                {
                    bufferG.setColor(Color.darkGray);
                } else
                {
                    bufferG.setColor(Color.gray);
                }

                bufferG.fillRect(275, 450, 350, 45);

                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 24));

                bufferG.drawString("Play Level", 675 + 115, 480);  

                bufferG.drawString("Play Level", 275 + 115, 480); 

                bufferG.setFont(new Font("Ariel", 1, 18));

                bufferG.drawString("To navigate back to the main menu, press BACKSPACE.", 425, 595); 
            } else {
                bufferG.setColor(Color.black);

                bufferG.fillRect(355, 65, 600, 400);

                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 25));

                bufferG.drawString(title, 550, 150);    

                bufferG.drawString("Made by Jacob Schnettler", 500, 200);   

                if (titleScreenIndex == 0)
                {
                    bufferG.setColor(Color.darkGray);
                } else
                {
                    bufferG.setColor(Color.gray);
                }

                bufferG.fillRect(425, 280, 450, 45);

                if (titleScreenIndex == 1)
                {
                    bufferG.setColor(Color.darkGray);
                } else
                {
                    bufferG.setColor(Color.gray);
                }

                bufferG.fillRect(425, 350, 450, 45);

                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 24));

                bufferG.drawString("Start Game", 470 + 115, 310);  

                bufferG.drawString("View Levels", 470 + 115, 380); 

                bufferG.setColor(Color.black);

                bufferG.fillRect(355, 500, 600, 100);

                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 18));

                bufferG.drawString("Use the arrow keys to navigate the menus, and enter to select.", 385, 555); 
            }
        } else if (showEndCredits || showDeathScreen)
        {
            bufferG.setColor(Color.black);

            bufferG.fillRect(355, 65, 600, 280);

            bufferG.fillRect(355, 365, 600, 180);

            bufferG.setColor(Color.white);

            bufferG.setFont(new Font("Ariel", 1, 30));

            bufferG.drawString(
                "Thank You for Playing!",
                490,
                150
            );

            bufferG.setFont(new Font("Ariel", 1, 25));

            bufferG.drawString(
                "Player Stats:",
                410,
                220
            );

            bufferG.drawString(
                "Player Score: " + playerScore,
                440,
                260
            );

            bufferG.drawString(
                "Trees Chopped: " + "1",
                680,
                260
            );

            bufferG.drawString(
                "Mobs Killed: " + mobsKilled,
                440,
                300
            );

            if (endScreenIndex == 0)
            {
                bufferG.setColor(Color.darkGray);
            } else
            {
                bufferG.setColor(Color.gray);
            }

            bufferG.fillRect(425, 400, 450, 45);

            if (endScreenIndex == 1)
            {
                bufferG.setColor(Color.darkGray);
            } else
            {
                bufferG.setColor(Color.gray);
            }

            bufferG.fillRect(425, 470, 450, 45);

            bufferG.setColor(Color.white);

            bufferG.drawString("Restart Game", 470 + 100, 430);  

            bufferG.drawString("Main Menu", 470 + 115, 500); 
        } else
        {
            bufferG.setColor(Color.red);

            bufferG.fillRect(0, 0, 1400, 700);

            Level level = Levels[currentLevel];

            drawLevel(bufferG, level);

            if (currentLevel == 1)
                bufferG.drawImage(caveOverlayIMG, 0, 0, 1400, 700, this);

            drawPlayer(bufferG);

            if (showTitleStartText)
            {
                bufferG.setColor(Color.black);

                bufferG.fillRect(450, 150, 550, 175);

                bufferG.setColor(Color.white);    

                bufferG.setFont(new Font("Ariel", 1, 25));

                bufferG.drawString("Forest Biome Level", 610, 220);

                bufferG.setFont(new Font("Ariel", 1, 15));

                bufferG.drawString("Your goal is to reach your house while avoiding mobs and enemys.", 500, 265);
            } else {
                bufferG.setColor(Color.white);

                // Creeper 1
                if (mobsKilled <= 0) {
                    if (scrollPosition == 5)
                        bufferG.drawImage(creeperTexture, creepers[0].getX(), creepers[0].getY() - 25, 165, 165, this);

                    if (scrollPosition == 6)
                        bufferG.drawImage(creeperTexture, creepers[0].getX(), creepers[0].getY() + 200, 165, 165, this);

                    if (scrollPosition == 7)
                        bufferG.drawImage(creeperTexture, creepers[0].getX(), creepers[0].getY() + 400, 165, 165, this);
                }

                // Creeper 2
                if (mobsKilled <= 1) {
                    if (scrollPosition == 7 )
                        bufferG.drawImage(creeperTexture, creepers[1].getX() + 350, creepers[1].getY() - 25, 165, 165, this);

                    if (scrollPosition == 8)
                        bufferG.drawImage(creeperTexture, creepers[1].getX() + 350, creepers[1].getY() + 200, 165, 165, this);

                    if (scrollPosition == 9)
                        bufferG.drawImage(creeperTexture, creepers[1].getX() + 350, creepers[1].getY() + 400, 165, 165, this);
                }

                // Creeper 3
                if (mobsKilled <= 2) {
                    if (scrollPosition == 9)
                        bufferG.drawImage(creeperTexture, creepers[2].getX() - 350, creepers[2].getY() - 25, 165, 165, this);

                    if (scrollPosition == 10)
                        bufferG.drawImage(creeperTexture, creepers[2].getX() - 350, creepers[2].getY() + 200, 165, 165, this);

                    if (scrollPosition == 11)
                        bufferG.drawImage(creeperTexture, creepers[2].getX() - 350, creepers[2].getY() + 400, 165, 165, this);
                }

                // Player UI Display
                bufferG.setColor(Color.black);

                bufferG.fillRect(25, 25, 350, 155);

                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 20));

                bufferG.drawString("Minecraft Frogger", 45, 55);

                bufferG.setFont(new Font("Ariel", 1, 18));

                bufferG.drawString("Level #" + (currentLevel + 1) + " - " + level.getTitle(), 45, 90);

                bufferG.drawString("Score: " + playerScore, 45, 130);

                // Alert UI Display
                if (showAlert)
                {
                    bufferG.setColor(Color.black);

                    bufferG.fillRect(925, 25, 450, 125);

                    bufferG.setColor(Color.white);

                    bufferG.setFont(new Font("Ariel", 1, 22));

                    bufferG.drawString(alertTitle, 945, 65);

                    bufferG.setFont(new Font("Ariel", 1, 18));

                    bufferG.drawString(alertText, 945, 105);
                }

                bufferG.setColor(Color.white);

                int result = Math.round((scrollPosition / (float) ((currentLevel == 0 ? levelOneLevelDataSize : levelTwoLevelDataSize) - 3)) * 320); // ChatGPT snippet!!

                bufferG.fillRect(50, 150, result, 10);

                bufferG.drawImage(playerHUD, 450, 620, 500, 60, this);

                if (choppedTrees)
                    bufferG.drawImage(hudWoodItem, 450, 620, 500, 60, this);

                if (swordEquiped)
                    bufferG.drawImage(hudSwordItem, 450, 620, 500, 60, this);

                for(int i = 0; i <= playerHealth - 1; i++)
                    bufferG.drawImage(hudHeartIcon, 455 + (i * 35), 580, 30, 30, this);
            }

            if (developerMode)
            {
                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 20));

                bufferG.drawString("Player X: " + playerX, 1200, 45);

                bufferG.drawString("Player Y: " + playerY, 1200, 90);
            }

            if (scrollPosition == 4 && playerX == 950 && !swordEquiped)
            {
                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 25));

                if (!choppedTrees)
                    bufferG.drawString("You have no wood to craft a sword.", 500, 350);
                else 
                    bufferG.drawString("Press E to craft a sword.", 550, 350);
            }

            if (scrollPosition == 0 && playerX == 250 && !choppedTrees)
            {
                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 25));

                bufferG.drawString("Press E to chop the tree.", 550, 350);
            }

            if (swordHitAvailable && holdingSword)
            {
                bufferG.setColor(Color.white);

                bufferG.setFont(new Font("Ariel", 1, 25));

                bufferG.drawString("Press E to attack the creeper.", 500, 300);
            }

            /*
            if (swordEquiped && developerMode)
            {
            bufferG.setColor(Color.white);

            bufferG.setFont(new Font("Ariel", 1, 25));

            bufferG.drawString("Sword equiped.", 250, 450);
            }
             */

            bufferG.setColor(Color.white);
            
            bufferG.setFont(new Font("Arel", 1, 20));
        }
        
        bufferG.setColor(Color.red);

        if (scrollPosition == 5)
            g.fillRect(creepers[0].getX(), creepers[0].getY() - 25, 165, 165);

        if (scrollPosition == 6)
            g.fillRect(creepers[0].getX(), creepers[0].getY() + 200, 165, 165);

        if (scrollPosition == 7)
            g.fillRect(creepers[0].getX(), creepers[0].getY() + 400, 165, 165);

        g.drawImage(buffer, 0, 0, this);
    }

    public void keyPressed(KeyEvent e)
    {
        Boolean checkMenuBtns = showTitleScreen || showLevelsPage;

        int code = e.getKeyCode();

        if (showEndCredits || showDeathScreen) {
            if (code == e.VK_UP && endScreenIndex != 0) {
                endScreenIndex--;
            } else if (code == e.VK_DOWN && endScreenIndex != 1) {
                endScreenIndex++;
            } else if (code == e.VK_ENTER) {
                if (endScreenIndex == 0)
                {
                    resetPlayerData();

                    if (currentLevel == 0)
                        startDefaultGame();
                    else 
                        startCaveLevel();
                } else {
                    resetPlayerData();

                    showEndCredits = false;
                    showDeathScreen = false;

                    showTitleScreen = true;
                }
            } 
        } else {
            if (checkMenuBtns)
            {
                if (showLevelsPage)
                {
                    if (code == e.VK_LEFT && selectedLevelIndex != 0) {
                        selectedLevelIndex--;
                    } else if (code == e.VK_RIGHT && selectedLevelIndex != 1) {
                        selectedLevelIndex++;
                    } else if (code == e.VK_ENTER) {
                        if (selectedLevelIndex == 0)
                        {
                            startDefaultGame();
                        } else {
                            startCaveLevel();
                        }
                    } else if (code == e.VK_BACK_SPACE) {
                        showLevelsPage = false;
                    }
                } else {
                    if (code == e.VK_UP && titleScreenIndex != 0) {
                        titleScreenIndex--;
                    } else if (code == e.VK_DOWN && titleScreenIndex != 1) {
                        titleScreenIndex++;
                    } else if (code == e.VK_ENTER) {
                        if (titleScreenIndex == 1)
                        {
                            showLevelsPage();
                        } else {
                            startDefaultGame();
                        }
                    }
                }
            } else {  
                if (showTitleStartText)
                {
                    showTitleStartText = false;
                } else if (!showEndCredits) {
                    if (code == e.VK_E && swordHitAvailable && holdingSword)
                    {
                        int newKillCount = mobsKilled + 1;

                        creepers[newKillCount - 1].setAlive(false);

                        mobsKilled = newKillCount;

                        showAlert = true;

                        alertTitle = "Creeper Killed!";
                        alertText = "You have gained +125 experience";
                   
                        playerScore = playerScore + 125;
                    } else if (code == e.VK_LEFT)
                    {
                        int newX = playerX - jumpDistance;

                        if (newX != -100)
                            playerX = newX;
                    } else if (code == e.VK_RIGHT)
                    {
                        int newX = playerX + jumpDistance;

                        if (newX != 1300)
                            playerX = newX;
                    } else if (code == e.VK_UP)
                    {
                        int newScroll = scrollPosition + 1;

                        if (newScroll == (currentLevel == 0 ? levelOneLevelDataSize  : levelTwoLevelDataSize) - 3)
                        {
                            showEndCredits = true;
                        } else {
                            scrollPosition = scrollPosition + 1;

                            showAlert = false;

                            playerScore = playerScore + 15;
                        }
                    } else if (code == e.VK_2 && swordEquiped) {
                        holdingSword = true;
                    } else if (code == e.VK_E && scrollPosition == 4 && playerX == 950 && !swordEquiped && choppedTrees)
                    {
                        swordEquiped = true;

                        showAlert = true;

                        alertTitle = "Sword Crafted!";
                        alertText = "Press 2 to equipd the item, and E to attack.";
                    } else if (code == e.VK_E && scrollPosition == 0 && playerX == 250 && !choppedTrees)
                    {
                        choppedTrees = true;

                        showAlert = true;

                        alertTitle = "Tree Chopped!";
                        alertText = "You have gained wood and +125 experience";
 
                        playerScore = playerScore + 125;
                    }

                    changeStep();
                }
            }
        }

        repaint();
    }

    public void keyReleased(KeyEvent e)
    {}

    public void keyTyped(KeyEvent e)
    {}

    public void run()
    {
        while (true)
        {
            repaint();

            // Creeper 1
            if (scrollPosition == 5)
                creeper1 = new Rectangle(creepers[0].getX(), creepers[0].getY() - 25, 165, 165);

            if (scrollPosition == 6)
                creeper1 = new Rectangle(creepers[0].getX(), creepers[0].getY() + 200, 165, 165);

            if (scrollPosition == 7)
                creeper1 = new Rectangle(creepers[0].getX(), creepers[0].getY() + 400, 165, 165);

            // Creeper 2
            if (scrollPosition == 7)
                creeper2 = new Rectangle(creepers[1].getX() + 350, creepers[1].getY() - 25, 165, 165);

            if (scrollPosition == 8)
                creeper2 = new Rectangle(creepers[1].getX() + 350, creepers[1].getY() + 200, 165, 165);

            if (scrollPosition == 9)
                creeper2 = new Rectangle(creepers[1].getX() + 350, creepers[1].getY() + 400, 165, 165);

            // Creeper 3
            if (scrollPosition == 7)
                creeper3 = new Rectangle(creepers[2].getX() - 350, creepers[2].getY() - 25, 165, 165);

            if (scrollPosition == 8)
                creeper3 = new Rectangle(creepers[2].getX() - 350, creepers[2].getY() + 200, 165, 165);

            if (scrollPosition == 9)
                creeper3 = new Rectangle(creepers[2].getX() - 350, creepers[2].getY() + 400, 165, 165);

            Rectangle steve = new Rectangle(playerX, playerY, 165, 165);

            Rectangle swordRadius = new Rectangle(
                    playerX - swordRadiusMultiplierX, playerY - swordRadiusMultiplierY, 165 + (swordRadiusMultiplierX * 2), 165 + (swordRadiusMultiplierY * 2)
                );

            if (steve.intersects(creeper1) && scrollPosition == 7)
            {
                playerHealth--;

                repaint();
            }

            if (steve.intersects(creeper2) && scrollPosition == 9)
            {
                playerHealth--;

                repaint();
            }

            if (steve.intersects(creeper3) && scrollPosition == 11)
            {
                playerHealth--;

                repaint();
            }

            if (swordRadius.intersects(creeper1) && scrollPosition == 6)
                swordHitAvailable = true;
            //else if (swordRadius.intersects(creeper2))
            //    swordHitAvailable = true;
            //else if (swordRadius.intersects(creeper3))
            //    swordHitAvailable = true;
            else 
                swordHitAvailable = false;

            if (playerHealth == 0)
            {
                showDeathScreen = true;

                repaint();
            }

            try {
                main.sleep(animDelay);
            } catch(Exception e) {          
            }
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
