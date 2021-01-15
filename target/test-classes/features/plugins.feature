Feature: Plugins

  @test1 @Sanity
  Scenario: Filter Free Plugins in UI
    Given I am ui user without account
    And I navigate to Free Plugins page
    When I verify that I am on 'Plugins' page
    Then I can see text '75 Results' search results are displayed on the page
    And I verify '30' app plugin cards are loaded on the page
    And I can see filter panel on the free plugins page
    And I click on 'Utility' search filter tag
    And I can see text '10 Results' search results are displayed on the page
    And I verify '10' app plugin cards are loaded on the page

  @test2  @API @Sanity
  Scenario: Filter Paid Plugins with API
    Given I am api user without account
    When I execute request to GET list of plugins
    |pricing| paid|
      |tags||
    And I verify for sale plugins response contains total '371' results
    Then I verify matching tags are displayed in for sale plugins response
      | tag               | value |
      | Additive          | 6     |
      | Algorithmic       | 9     |
      | Amp-Simulator     | 8     |
      | Analog            | 52    |
      | Arpeggiator       | 5     |
      | Arragement        | 1     |
      | Arrangement       | 1     |
      | Audio-Repair      | 1     |
      | Audio-Restoration | 5     |
      | Audio-editing     | 1     |
      | Autotune          |8     |
      | Band-Pass         | 1     |
      | Bass              | 7     |
      | Beat-Processing   | 1     |
      | Bitcrusher        | 3     |
      | Channel-Strip     | 17    |
      | Chord             | 2     |
      | Chorus            | 6     |
      | Composition       | 2     |
      | Compression       | 7     |
      | Compressor        | 44    |
      | Convolution       | 4     |
      | DAW               | 3     |
      | DSP               | 1     |
      | De-Buzz           | 1     |
      | De-Esser          | 6     |
      | De-Hum            | 1     |
      | De-Noise          | 2     |
      | Delay             | 14    |
      | Distortion        | 28    |
      | Drum-Machine      | 5     |
      | Drums             | 15    |
    And I execute request to GET list of plugins
      |pricing| paid|
      |tags|Bass|
    And I verify for sale plugins response contains total '7' results




