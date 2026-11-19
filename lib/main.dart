
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const PashtoKeyboardApp());
}

class PashtoKeyboardApp extends StatelessWidget {
  const PashtoKeyboardApp({super.key});

  static const platform = MethodChannel('pashto_keyboard');

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Pashto Keyboard',
      theme: ThemeData(
        colorSchemeSeed: Colors.green,
        useMaterial3: true,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: const Text('پښتو کیبورډ'),
          centerTitle: true,
        ),
        body: Center(
          child: Padding(
            padding: const EdgeInsets.all(24),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Icon(
                  Icons.keyboard,
                  size: 90,
                  color: Colors.green,
                ),
                const SizedBox(height: 20),
                const Text(
                  'پښتو کیبورډ ته ښه راغلاست!',
                  style: TextStyle(
                    fontSize: 22,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(height: 15),
                const Text(
                  'د پښتو ژبې لپاره خپل نوی کیبورډ فعال کړئ.',
                  textAlign: TextAlign.center,
                ),
                const SizedBox(height: 30),
                FilledButton.icon(
                  onPressed: () {
                    platform.invokeMethod('openKeyboardSettings');
                  },
                  icon: const Icon(Icons.settings),
                  label: const Text('کیبورډ فعال کړئ'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
