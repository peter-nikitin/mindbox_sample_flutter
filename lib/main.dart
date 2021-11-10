import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'package:mindbox/mindbox.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  final config = Configuration(
      domain: "api.mindbox.ru",
      endpointIos: "mpush-test-ios-sandbox-docs",
      endpointAndroid: "mpush-test-android-sandbox-docs",
      subscribeCustomerIfCreated: true);

  Mindbox.instance.init(configuration: config);

  Mindbox.instance.onPushClickReceived(print); 

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mindbox Sample',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Mindbox Sampla App'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _deviceUUID = "SDK еще не инициализировано";

  void _setDeviceUUID(uuid) {
    setState(() {
      _deviceUUID = uuid;
    });
  }

  @override
  void initState() {
    if (!mounted) return;
    Mindbox.instance.getDeviceUUID((device) {
      _setDeviceUUID(device);
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'Ваш deviceUUID:',
            ),
            Text(
              _deviceUUID,
            ),
            TextButton(
              style: ButtonStyle(
                foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
              ),
              onPressed: () {
                Clipboard.setData(ClipboardData(text: _deviceUUID));
              },
              child: Text('Копировать deviceUUID'),
            )
          ],
        ),
      ),
    );
  }
}
